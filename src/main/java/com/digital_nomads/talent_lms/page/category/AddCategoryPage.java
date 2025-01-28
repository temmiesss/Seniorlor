package com.digital_nomads.talent_lms.page.category;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCategoryPage {
    @FindBy(xpath = "//input[@name='name']")
    public WebElement categoryName;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    public  WebElement selectorOfParentCategory;

    @FindBy(xpath = "//*[@id='show-price']")
    public WebElement priceSetter;

    @FindBy(xpath = "//input[@name='submit_category']")
    public WebElement addCategoryBtn;

    @FindBy(xpath = "//a[normalize-space()='cancel']")
    public WebElement cancelCategoryBtn;
}
