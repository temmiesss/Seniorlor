package page;

import drivers.Driver;
import helper.WebElementActions;
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
