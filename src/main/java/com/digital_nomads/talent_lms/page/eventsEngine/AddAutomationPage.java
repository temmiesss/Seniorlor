package com.digital_nomads.talent_lms.page.eventsEngine;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddAutomationPage {

    @FindBy(xpath = "//a[normalize-space(text()) = 'Add automation']")
    public WebElement addAutomation;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameAutomationInput;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    public WebElement selectAutomationContainer;

    @FindBy(xpath = "//input[@value='Add automation']")
    public WebElement createAutomationButton;

    @FindBy(xpath = "//a[normalize-space(text()) = 'cancel']")
    public WebElement cancelCreatingAutomation;


}
