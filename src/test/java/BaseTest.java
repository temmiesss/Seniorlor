import drivers.Driver;
import helper.AlertHelper;
import helper.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {
    public static WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;

    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);

    }
}
