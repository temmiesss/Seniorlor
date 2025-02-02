package com.digital_nomads.talent_lms.page.addGroup;

import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @FindBy(xpath = "//*[@id=\"tl-groups-grid\"]/tbody/tr/td[3]/div")
    private WebElement textLine;

    @FindBy(xpath = "//*[@id=\"tl-groups-grid\"]/tbody/tr/td[3]/div/div/i[1]")
    private WebElement editPencil;

    @FindBy(xpath = "//*[@id=\"tl-admin-dashboard\"]/div[4]/div/div[1]/a")
    private WebElement enterGroup;

    @FindBy(xpath = "//input[@name='name' and @placeholder='e.g. Startup courses']")
    private WebElement editName;

    @FindBy(xpath = "//textarea[@name='description' and @placeholder='Short description up to 500 characters']")
    private WebElement editDescription;

    @FindBy(xpath = "//input[@name='submit_group']")
    private WebElement updateGroup;

    @FindBy(xpath = "//a[@class='btn btn-danger' and @id='tl-confirm-submit']")
    private WebElement deleteGroup;

    /**
     * Метод для добавления новой группы.
     * @param group Объект группы, содержащий информацию о названии и описании группы.
     * @return Возвращает объект текущей страницы для продолжения работы с ней.
     */
    public AddGroupPage addNewGroup(Groups group) {
        webElementActions.click(addGroupBtn)
                .click(inputName)
                .sendKeys(this.inputName, group.getName())
                .click(description)
                .sendKeys(this.description, group.getDescription());
        webElementActions.click(submitBtn);
        return new AddGroupPage();
    }

    /**
     * Метод для редактирования существующей группы.
     * @param group Объект группы, содержащий новые данные для редактирования.
     * @return Возвращает объект текущей страницы для продолжения работы с ней.
     */
    public AddGroupPage editGroup(Groups group) {
        webElementActions.click(enterGroup)
                .moveToElement(textLine)
                .click(editPencil)
                .click(editName)
                .sendKeys(editName, "Test Name")
                .click(editDescription)
                .sendKeys(editDescription, "Test Description")
                .click(updateGroup);
        return new AddGroupPage();
    }

    /**
     * Метод для проверки добавления группы.
     * @param group Объект группы, которую нужно проверить.
     * @return Возвращает true, если группа была добавлена, иначе false.
     */

    public boolean isGroupAdded(Groups group) {
        String xpath = "//td[normalize-space(text()) = '" + group.getName() + "']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
        return true;
    }

    /**
     * Метод для удаления группы.
     * @param group Объект группы, которую нужно удалить.
     * @return Возвращает объект текущей страницы для продолжения работы с ней.
     */
    public AddGroupPage deleteGroup(Groups group) {
        webElementActions.click(enterGroup)
                .moveToElement(textLine)
                .click(textLine)
                .click(deleteGroup);
        return new AddGroupPage();
    }
}







