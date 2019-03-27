package ru.aplana.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div//div[@class=\"service__links\"]//a[@href=\"/contributions/\"]")
    public WebElement contributionsBtn;
}