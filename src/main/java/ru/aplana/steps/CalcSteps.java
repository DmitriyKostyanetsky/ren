package ru.aplana.steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import ru.aplana.pages.CalcPage;
import ru.aplana.util.Init;

import java.util.ArrayList;
import java.util.List;

public class CalcSteps {

    private CalcPage calcPage = new CalcPage();
    private Actions action = new Actions(Init.getDriver());

    private List<String> expectDollars = new ArrayList<String>() {{
        add("0.75");
        add("301,42");
        add("8000");
        add("58301,42");
    }};

    private List<String> expectRubles = new ArrayList<String>() {{
        add("6.25");
        add("65132,87");
        add("150000");
        add("2215132,87");
    }};

    @Когда("проверить заголовок – Рассчитайте доходность по вкладам")
    public boolean checkTitle() {
        if (calcPage.isElementPresent(calcPage.titleText)) {
            return calcPage.titleText.getText().equals("Рассчитайте доходность по вкладу");
        }
        return false;
    }

    @Когда("выбрать валюту String \"(.+)\"")
    public void chooseValue(String value) {
        if (value.equals("Доллары")) {
            calcPage.clickOnElement(calcPage.dollarBtn);
        } else if (value.equals("Рубли")) {
            calcPage.clickOnElement(calcPage.rubleBtn);
            calcPage.clickOnElement(calcPage.openDepositCheckBox);
        }
    }

    @Когда("выбрать сумму вклада int \"(.+)\"")
    public void chooseDepositAmount(Integer amount) {
        calcPage.clickOnElement(calcPage.amountField);
        calcPage.inputInField(calcPage.amountField, amount.toString());
    }

    @Когда("выбрать срок String \"(.+)\"")
    public void chooseTerm(String value) {
        calcPage.clickOnElement(calcPage.periodBox);
        Select select = new Select(Init.getDriver().findElement(By.xpath("//select[@class=\"calculator__slide-input js-slide-value\"]")));
        select.selectByVisibleText(value);
    }

    @Когда("выбрать ежемесячное пополнение int \"(.+)\"")
    public void selectMonthly(Integer amount) {
        calcPage.clickOnElement(calcPage.replenishField);
        calcPage.inputInField(calcPage.replenishField, amount.toString());
    }

    @Когда("выбрать частичное снятие, если вклад в рублях String \"(.+)\"")
    public void partialWithdrawal(String value) {
        if (value.equals("Рубли")) {
            calcPage.clickOnElement(calcPage.partialCheckBox);
        }
    }

    @Когда("отметить чекбокс ежемесячная капитализация")
    public void checkBox() {
        calcPage.clickOnElement(calcPage.capitalizationCheckBox);
    }

    @Тогда("проверить введенные значения String \"(.+)\"")
    public void checkEnteredValues(String value) throws InterruptedException {
        action.pause(2000);
        action.perform();

        List<String> expectedList = new ArrayList<>();
        if (value.equals("Доллары")) {
            expectedList = expectDollars;
        } else if (value.equals("Рубли")) {
            expectedList = expectRubles;
        }
        Assert.assertEquals(expectedList.get(0), calcPage.percentText.getText().replace("%", ""));
        Assert.assertEquals(expectedList.get(1), calcPage.accruedText.getText().replace(" ", ""));
        Assert.assertEquals(expectedList.get(2), calcPage.replenishText.getText().replace(" ", ""));
        Assert.assertEquals(expectedList.get(3), calcPage.resultText.getText().replace(" ", ""));
    }
}