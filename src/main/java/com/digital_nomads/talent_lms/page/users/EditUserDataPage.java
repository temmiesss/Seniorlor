package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Akylai
 * Методы изменения данных пользователя
 */
public class EditUserDataPage extends BasePage {

    @FindBy(xpath = "//td[@class=' tl-align-left hidden-phone']/span[@title='Learner-Type']")
    public WebElement changeLearnerType;

    @FindBy(xpath = "(//div/i[@class='icon-pencil icon-grid' and @alt='Edit'])[2]")
    public WebElement edit;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement editFirstNameField;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='surname']")
    public WebElement editLastNameField;

    @FindBy(xpath = "//select[@name='acl_user_type_id']/../div/a/span[@class='select2-chosen']")
    public WebElement editUserTypeField;

    @FindBy(css = "div.select2-drop.select2-display-none.select2-drop-auto-width.select2-drop-active > ul")
    public WebElement dropdownList;

    @FindBy(xpath = "//select[@name='timezone']/../div/a/span[@class='select2-chosen']")
    public WebElement editTimeZoneField;

    @FindBy(xpath = "//select[@name='language']/../div/a/span[@class='select2-chosen']")
    public WebElement editLanguageField;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement updateUserBTN;

    public EditUserDataPage editUserFirstName(User user){
        webElementActions.click(changeLearnerType).click(edit)
                .click(editFirstNameField).clear(editFirstNameField)
                .sendKeys(editFirstNameField, user.getFirstName())
                .click(updateUserBTN);
        return this;
    }

    public EditUserDataPage editUserLastName(User user){
        webElementActions.click(changeLearnerType).click(edit)
                .click(editLastNameField).clear(editLastNameField)
                .sendKeys(editLastNameField, user.getLastName())
                .click(updateUserBTN);
        return this;
    }

    public EditUserDataPage editUserType(String userType){
        webElementActions.click(changeLearnerType).click(edit)
                .click(editUserTypeField);
        WebElement userTypeOption = dropdownList.findElement(By.xpath(".//li/div[text()='" + userType + "']"));
        webElementActions.click(userTypeOption).click(updateUserBTN);
        return this;
    }

    public EditUserDataPage editTimeZone(String timeZone){
        webElementActions.click(changeLearnerType).click(edit)
                .click(editTimeZoneField);
        WebElement timeZoneOption = dropdownList.findElement(By.xpath(".//li/div[text()='" + timeZone + "']"));
        webElementActions.click(timeZoneOption).click(updateUserBTN);
        return this;
    }

    public EditUserDataPage editLanguage(String language){
        webElementActions.click(changeLearnerType).click(edit)
                .click(editLanguageField);
        WebElement languageOption = dropdownList.findElement(By.xpath(".//li/div[text()='" + language + "']"));
        webElementActions.click(languageOption).click(updateUserBTN);
        return this;
    }

    // Добавляем геттеры для обновленного поля имени и фамилии
    public WebElement getUpdatedFirstNameField() {
        return editFirstNameField;
    }

    public WebElement getUpdatedLastNameField() {
        return editLastNameField;
    }

    public WebElement getUpdatedUserTypeField() {
        return editUserTypeField;
    }

    public WebElement getUpdateTimeZoneField(){
        return editTimeZoneField;
    }

    public WebElement getUpdatedLanguageField(){
        return editLanguageField;
    }
}