package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.digital_nomads.talent_lms.page.BasePage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement bioInput;

    @FindBy(name = "acl_user_type_id")
    public WebElement userTypeDropdown;

    @FindBy(name = "timezone")
    public WebElement timeZoneDropdown;

    @FindBy(name = "language")
    public WebElement languageDropdown;

    @FindBy(xpath = "//input[@name='restrict_email']")
    public WebElement excludeFromEmailTick;

    @FindBy(id = "fileupload_input")
    public WebElement fileUpload;

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    public WebElement applyBtn;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    @FindBy(xpath = "//a[text()='cancel']")
    public WebElement cancelAddUserBtn;

    /**
     * Добавление нового пользователя в систему
     *
     * @param user Объект класса `User`, содержащий данные нового пользователя
     * @return Возвращает новый объект `UserPage`, что указывает на успешный переход
     * на страницу со списком пользователей после добавления нового пользователя.
     */
    public UserPage addNewUser(User user) {
        webElementActions.sendKeys(firstNameInput, user.getFirstName())
                .sendKeys(lastNameInput, user.getLastName())
                .sendKeys(emailInput, user.getEmail())
                .sendKeys(usernameInput, user.getUsername())
                .sendKeys(passwordInput, "TestPassed123$")
                .sendKeys(bioInput, "Hello world!");
        // Случайный выбор значений из выпадающих списков
        selectRandomUserType();
        selectRandomTimeZone();
        selectRandomLanguage();

        webElementActions.click(excludeFromEmailTick).click(addUserButton);
        return new UserPage();
    }

    /**
     * Метод для случайного выбора типа пользователя
     */
    private void selectRandomUserType() {
        Select userTypeSelect = new Select(userTypeDropdown);
        List<WebElement> options = userTypeSelect.getOptions();
        int randomIndex = new Random().nextInt(options.size());
        userTypeSelect.selectByIndex(randomIndex);
    }

    /**
     * Метод для случайного выбора часового пояса
     */
    private void selectRandomTimeZone() {
        Select timeZoneSelect = new Select(timeZoneDropdown);
        List<WebElement> options = timeZoneSelect.getOptions();
        int randomIndex = new Random().nextInt(options.size());
        timeZoneSelect.selectByIndex(randomIndex);
    }

    /**
     * Метод для случайного выбора языка
     */
    private void selectRandomLanguage() {
        Select languageSelect = new Select(languageDropdown);
        List<WebElement> options = languageSelect.getOptions();
        int randomIndex = new Random().nextInt(options.size());
        languageSelect.selectByIndex(randomIndex);
    }

    /**
     * Получаем сообщение успешного добавления пользователя
     * Используется в тестовом методе testAddNewUser() в классе AddNewUserTest
     *
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
     *
     * @return Возвращает страницу, где отображается "addUserButton"
     */
    public boolean isPageLoaded() {
        return addUserButton.isDisplayed();
    }

}