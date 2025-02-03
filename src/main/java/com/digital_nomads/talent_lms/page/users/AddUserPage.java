package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.digital_nomads.talent_lms.page.BasePage;
import java.time.Duration;

/**
 * @author Akylai
 */
public class AddUserPage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement userNameInput;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "(//span[@class='select2-arrow'])[1]")
    public WebElement openUserTypes;

    @FindBy(xpath = "(//span[@class='select2-chosen'])[2]")
    public WebElement timeZoneSelectBtn;

    @FindBy(xpath = "//span[contains(text(),'(English)')]")
    public WebElement languageSelectInput;

    @FindBy(xpath = "//input[@name='restrict_email']")
    public WebElement excludeFromEmailTick;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    @FindBy(xpath = "//a[text()='cancel']")
    public WebElement cancelAddUserBtn;

    /**
     * Добавление нового пользователя в систему
     * @param user Объект класса `User`, содержащий данные нового пользователя
     * @return Возвращает новый объект `UserPage`, что указывает на успешный переход
     * на страницу со списком пользователей после добавления нового пользователя.
     */
    public UserPage addNewUser(User user) {
        webElementActions.sendKeys(firstNameInput, user.getFirstName())
                .sendKeys(lastNameInput, user.getLastName())
                .sendKeys(emailInput, user.getEmail())
                .sendKeys(userNameInput, user.getUsername())
                .sendKeys(passwordInput, user.getPassword())
                .click(excludeFromEmailTick).click(addUserButton);
        return new UserPage();
    }
    /**
     * Добавление пользователя с некорректной эл.почтой
     * @param user Заполняет те же поля, что и addNewUser
     * @return Возвращает текущий объект AddUserPage
     */
    public AddUserPage addNewUserWithInvalidEmail(User user, String invalidEmail) {
        webElementActions.sendKeys(firstNameInput, user.getFirstName())
                .sendKeys(lastNameInput, user.getLastName())
                .sendKeys(emailInput, invalidEmail)
                .sendKeys(userNameInput, user.getUsername())
                .sendKeys(passwordInput, user.getPassword())
                .click(addUserButton);
        return this;
    }

    /**
     * Получаем сообщение успешного добавления пользователя
     * @return Возвращает текст сообщения
     */
    public String getAddUserSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='toast-message']")));

        // Сохраняем текст через JavaScript (даже если элемент исчезнет)
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String messageText = (String) js.executeScript("return arguments[0].innerText;", messageElement);
        return messageText;
    }

    public UserPage cancelAddUser() {
        webElementActions.click(cancelAddUserBtn);
        return new UserPage();
    }

    /**
     * Проверяет наличие заголовка или одного из элементов страницы
     * @return Возвращает страницу, где отображается "addUserButton"
     */
    public boolean isPageLoaded() {
        return addUserButton.isDisplayed();
    }
}