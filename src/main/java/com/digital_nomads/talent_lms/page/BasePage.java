package com.digital_nomads.talent_lms.page;

import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.drivers.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BasePage {

    public WebElementActions webElementActions = new WebElementActions();
    public WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    public Random random = new Random();
    public WebDriver driver;
    public Actions actions = new Actions(Driver.getDriver());
  
    public BasePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
}
