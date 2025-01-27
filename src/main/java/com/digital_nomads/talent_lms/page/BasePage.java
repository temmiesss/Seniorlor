package com.digital_nomads.talent_lms.page;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebElementActions webElementActions = new WebElementActions();

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
