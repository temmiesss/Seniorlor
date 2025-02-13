package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * @author Akylai
 * Работа с некорректными данными при добавлении пользователя
 */
public class AddUserWithInvalidData extends BasePage {
    private AddUserPage addUserPage = new AddUserPage();

    @FindBy(xpath = "//input[@name='name']/../../span/span")
    public WebElement invalidFirstNameInputMessage;

    @FindBy(xpath = "//input[@name='surname']/../../span/span")
    public WebElement invalidLastNameInputMessage;

    @FindBy(xpath = "//input[@name='email']/../../span/span")
    public WebElement invalidEmailInputMessage;

    @FindBy(xpath = "//input[@name='login']/../../span/span")
    public WebElement invalidUsernameInputMessage;

    @FindBy(xpath = "//input[@name='password']/../../span/span")
    public WebElement invalidPasswordInputMessage;

    @FindBy(xpath = "//textarea[@name='description']/../../span/span")
    public WebElement invalidBioInputMessage;


    public String getInvalidFirstNameMessage() {
        return invalidFirstNameInputMessage.getText();
    }

    public String getInvalidLastNameMessage() {
        return invalidLastNameInputMessage.getText();
    }

    public String getInvalidEmailMessage() {
        return invalidEmailInputMessage.getText();
    }

    public String getInvalidUsernameMessage() {
        return invalidUsernameInputMessage.getText();
    }

    public String getInvalidPasswordMessage() {
        return invalidPasswordInputMessage.getText();
    }

    public String getInvalidBioMessage() {
        return invalidBioInputMessage.getText();
    }

    /**
     * Пытаемся добавить пользователя с некорректыми данными
     *
     * @param user - пользователь, данные которого используются
     * @param invalidData - некорректные значения данных, которые подставляются для проверки
     * @return Возвращает текущий объект AddUserWithInvalidData
     */
    public AddUserWithInvalidData addInvalidUser(User user, Map<String, String> invalidData) {
        webElementActions.sendKeys(addUserPage.firstNameInput, invalidData.getOrDefault("firstName", user.getFirstName()))
                .sendKeys(addUserPage.lastNameInput, invalidData.getOrDefault("lastName", user.getLastName()))
                .sendKeys(addUserPage.emailInput, invalidData.getOrDefault("email", user.getEmail()));
        addUserPage.usernameInput.clear();
        webElementActions.sendKeys(addUserPage.usernameInput, invalidData.getOrDefault("username", user.getUsername()))
                .sendKeys(addUserPage.passwordInput, invalidData.getOrDefault("password", "TestPassed123$"))
                .sendKeys(addUserPage.bioInput, invalidData.getOrDefault("bio", user.getBio()))
                .click(addUserPage.addUserButton);
        return this;
    }
}
