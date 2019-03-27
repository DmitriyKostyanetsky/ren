package ru.aplana.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.util.Init;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Init.getDriver(), this);
    }

    public void clickOnElement(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 30, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void inputInField(WebElement element, String inputText) {
        element.clear();
        element.sendKeys(inputText);
    }

    /**
     * Проскролить до элемента
     */
    public void scrollDown(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Init.getDriver();
        Assert.assertNotNull(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public boolean isElementPresent(WebElement element) {
        try {
            Init.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            Init.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }
}