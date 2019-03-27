package ru.aplana.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalcPage extends BasePage {

    @FindBy(xpath = "//div//h2[contains(text(), 'Рассчитайте доходность по вкладу')]")
    public WebElement titleText;

    @FindBy(xpath = "//div//span[contains(text(),\"В отделении банка\")]//preceding::input[@name=\"deposit_b_n\"]//parent::div")
    public WebElement openDepositCheckBox;

    @FindBy(xpath = "//span[text()=\"Рубли\"]")
    public WebElement rubleBtn;

    @FindBy(xpath = "//span[text()=\"Доллары США\"]")
    public WebElement dollarBtn;

    @FindBy(xpath = "//input[@name=\"amount\"]")
    public WebElement amountField;

    @FindBy(xpath = "//div[@class=\"jq-selectbox__select\"]")
    public WebElement periodBox;

    @FindBy(xpath = "//input[@name=\"replenish\"]")
    public WebElement replenishField;

    @FindBy(xpath = "//input[@name=\"capitalization\"]//parent::div")
    public WebElement capitalizationCheckBox;

    @FindBy(xpath = "//input[@name=\"partial_out\"]//parent::div")
    public WebElement partialCheckBox;

    @FindBy(xpath = "//div[@class=\"calculator__dep-percent\"]//child::span[@class=\"js-calc-rate\"]")
    public WebElement percentText;

    @FindBy(xpath = "//tr//td//child::b//child::span[@class=\"js-calc-earned\"]")
    public WebElement accruedText;

    @FindBy(xpath = "//tr//td//child::span[@class=\"js-calc-replenish\"]")
    public WebElement replenishText;

    @FindBy(xpath = "//div//div//child::span[@class=\"js-calc-result\"]")
    public  WebElement resultText;
}