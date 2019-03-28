package ru.aplana.steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.pages.CalcPage;
import ru.aplana.util.Init;

public class CalcSteps {

    private CalcPage calcPage = new CalcPage();

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

    @Тогда("проверить введенные значения String \"(.+)\" String \"(.+)\" String \"(.+)\" String \"(.+)\"")
    public void checkEnteredValues(String exPercent, String exAccrued, String exReplenishment, String exRemoval) {
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 30);
        wait.withMessage(String.format("Ожидалось значение [%s]", exPercent))
                .until((ExpectedCondition<Boolean>) driver -> {
                    if (calcPage.percentText.getText().replace("%", "").replaceAll(" ", "").equals(exPercent)
                            && calcPage.accruedText.getText().replace("%", "").replaceAll(" ", "").equals(exAccrued)
                            && calcPage.replenishText.getText().replace("%", "").replaceAll(" ", "").equals(exReplenishment)
                            && calcPage.resultText.getText().replace("%", "").replaceAll(" ", "").equals(exRemoval)) {
                        return Boolean.TRUE;
                    }
                    return false;
                });
    }
}