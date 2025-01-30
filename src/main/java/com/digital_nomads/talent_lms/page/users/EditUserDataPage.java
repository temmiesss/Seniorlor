package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditUserDataPage extends BasePage {

    @FindBy(xpath = "//td[@class=' tl-align-left hidden-phone']/span[@title='Learner-Type']")
    public WebElement changeLearnerType;

    @FindBy(xpath = "(//div/i[@class='icon-pencil icon-grid' and @alt='Edit'])[2]")
    public WebElement edit;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement editFirstNameField;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement updateUserBTN;

    @FindBy(xpath = "//input[@data-id='2']")
    public WebElement checkboxOnTick;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    public WebElement massActionsBtn;

    @FindBy(xpath = "//a[normalize-space()='Delete']")
    public WebElement deleteUser;

    @FindBy(xpath = "//a[@id='submit-mass-action']")
    public WebElement deleteRedIcon;

    public EditUserDataPage editUserFirstName(User user){
        webElementActions.click(changeLearnerType).click(edit)
                .click(editFirstNameField).clear(editFirstNameField)
                .sendKeys(editFirstNameField, user.getFirstName())
                .click(updateUserBTN);
        return this;
    }

    // **Добавляем геттер для обновленного поля имени**
    public WebElement getUpdatedFirstNameField() {
        return editFirstNameField;
    }

    public EditUserDataPage deleteUser(){
        webElementActions.click(checkboxOnTick).click(massActionsBtn).click(deleteUser)
                .click(deleteRedIcon);
        return this;
    }



}
