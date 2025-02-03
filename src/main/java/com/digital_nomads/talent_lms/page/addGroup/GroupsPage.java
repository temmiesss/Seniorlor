package com.digital_nomads.talent_lms.page.addGroup;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Saida
 */


public class GroupsPage extends BasePage {

    @FindBy(xpath = "//*[@class='icon-ellipsis-h tl-table-operations-icon']")
    public WebElement textLine;

    @FindBy (xpath = "//*[@title='Edit']")
    public WebElement editPencil;

    @FindBy (xpath = "//*[@value='TestGroup']")
    public WebElement expectedName;

    @FindBy (xpath = "//*[@placeholder='Short description up to 500 characters']")
    public WebElement expectedDescription;

    @FindBy (xpath = "//*[@id='group-key']") // &&&
    public WebElement expectedKey;

    @FindBy (xpath = "//*[@name='max_redemptions']")
    public WebElement nameRequired;

    @FindBy (xpath = "//a[@title='Groups']")
    public WebElement groupList;

}
