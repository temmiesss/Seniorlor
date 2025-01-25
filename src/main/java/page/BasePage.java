package page;

import drivers.Driver;
import helper.WebElementActions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebElementActions webElementActions = new WebElementActions();

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
