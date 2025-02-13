package com.digital_nomads.talent_lms.page.category;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCategoryPage extends BasePage {
    @FindBy(xpath = "//input[@name='name']")
    public WebElement categoryName;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    public  WebElement selectorOfParentCategory;

    // до нажатия на кнопку xpath
    @FindBy(xpath = "//*[@id='show-price']")
    public WebElement priceSetter;

    // после нажатия на кнопку xpath
    @FindBy(xpath = "//*[@name='price']")
    public WebElement priceSetter2ndStage;

    @FindBy(xpath = "//input[@name='submit_category']")
    public WebElement addCategoryBtn;

    @FindBy(xpath = "//a[normalize-space()='cancel']")
    public WebElement cancelCategoryBtn;
}
