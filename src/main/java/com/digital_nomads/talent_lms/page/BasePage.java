package com.digital_nomads.talent_lms.page;

import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.page.users.AddUserPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BasePage {

    public WebElementActions webElementActions = new WebElementActions();
    public WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    public Random random = new Random();

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
