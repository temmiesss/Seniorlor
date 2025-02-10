package com.digital_nomads.talent_lms.helper;

import com.digital_nomads.talent_lms.drivers.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static com.digital_nomads.talent_lms.drivers.Driver.driver;


public class WebElementActions {
    public Actions actions = new Actions(Driver.getDriver());

    public WebElementActions waitButtonBeClickable(WebElement element){
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public WebElementActions waitElementToBeDisplayed(WebElement element){
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public WebElementActions click(WebElement element){
        waitElementToBeDisplayed(element);
        waitButtonBeClickable(element);
        highlightElement(element);
        element.click();
        return this;
    }

    public WebElementActions jsClick(WebElement element){
        waitElementToBeDisplayed(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
        return this;

    }

    public void pause(Integer milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElementActions navigateBack() {
        Driver.getDriver().navigate().back();
        return this;
    }

    public WebElementActions sendKeys(WebElement element, String text){
        waitButtonBeClickable(element);
        highlightElement(element);
        element.sendKeys(text);
        return this;
    }

    public  WebElementActions doubleClick(WebElement element){
        waitElementToBeDisplayed(element);
        waitButtonBeClickable(element);
        actions.doubleClick(element).perform();
        return this;
    }

    public WebElementActions rightClick(WebElement element){
        waitButtonBeClickable(element);
        waitElementToBeDisplayed(element);
        actions.contextClick(element).perform();
        return this;
    }

    public WebElementActions scrollToElementWithActions(WebElement element){
        waitElementToBeDisplayed(element);
        actions.moveToElement(element).perform();
        return this;
    }

    public WebElementActions scrollToElement(WebElement element){
        waitElementToBeDisplayed(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public WebElementActions highlightElement(WebElement element){
        waitElementToBeDisplayed(element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].style.border='3px solid yellow'", element);
        return this;
    }


    public WebElementActions moveToElement(WebElement element){
        waitElementToBeDisplayed(element);
        highlightElement(element);
        actions.moveToElement(element).perform();
        return this;
    }

    public WebElementActions clear(WebElement element) {
        waitButtonBeClickable(element);
        element.clear();
        if (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        }
        return this;
    }

    public boolean isDisplayed(WebElement element){
       return element.isDisplayed();
    }

}
