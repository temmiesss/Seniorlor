package com.digital_nomads.talent_lms.page.addGroup;

import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.digital_nomads.talent_lms.drivers.Driver.driver;

/**
 * @author Saida
 */

public class AddGroupPage extends BasePage {

    public WebElementActions webElementActions = new WebElementActions();

    @FindBy(xpath = "//div[@class='tl-icons-block']//a[contains(text(), 'Add group')]")
    private WebElement addGroupBtn;

    @FindBy(xpath = "//input[@data-maxchars='80']")
    private WebElement inputName;

    @FindBy(xpath = "//*[@placeholder='Short description up to 500 characters']")
    private WebElement description;

    @FindBy(id = "show-key")
    private WebElement groupKey;

    @FindBy(name = "group_key")
    private WebElement keyInput;

    @FindBy(id = "show-price")
    private WebElement price;

    @FindBy(name = "price")
    private WebElement priceInputField;

    @FindBy(xpath = "//input[@class='btn btn-primary' and @value='Add group']")
    private WebElement submitBtn;

    @FindBy(xpath = "//a[contains(@class, 'groups-button')]")
    private WebElement groupsBtn;

    @FindBy(xpath = "//a[@title='Groups']")
    private WebElement groupsNames;

    @FindBy(xpath = "(//i [@class = 'icon-ellipsis-h tl-table-operations-icon'])[2]")
    private WebElement textLine;

    @FindBy(xpath = "(//i [@class = 'icon-pencil icon-grid'])[2]")
    private WebElement editPencil;

    @FindBy(xpath = "//input[@name='submit_group']")
    private WebElement updateGroup;

    @FindBy(xpath = "//*[(//a[contains(@class, 'btn-danger') and contains(text(), 'Delete')])[2]")
    private WebElement deleteGroup;


    public AddGroupPage addNewGroup(Groups group) {
        webElementActions.click(addGroupBtn)
                .click(inputName)
                .sendKeys(this.inputName, group.getName())
                .click(description)
                .sendKeys(this.description, group.getDescription());
        webElementActions.click(submitBtn);
        return new AddGroupPage();
    }




}

