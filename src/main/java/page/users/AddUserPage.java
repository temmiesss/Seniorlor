package page.users;

import drivers.Driver;
import entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.BasePage;
import java.util.Random;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//span[text()='Learner-Type']/ancestor::a")
    public WebElement openUserTypes;

    @FindBy(xpath = "//span[contains(text(),'Greenwich')]/parent::a")
    public WebElement openTimeZone;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserBtn;

    @FindBy(xpath = "//a[text()='cancel']")
    public WebElement cancelAddUserBtn;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    public UserPage addNewUser(User user) {
        webElementActions.sendKeys(firstNameInput, user.getFirstName())
                .sendKeys(lastNameInput, user.getLastName())
                .sendKeys(emailInput, user.getEmail())
                .sendKeys(userNameInput, user.getUsername())
                .sendKeys(passwordInput, "QwerTy123%$")
                .click(addUserButton);
        return new UserPage();
    }

    /**
     * Добавление пользователя с некорректной эл.почтой
     * @param user Заполняет те же поля, что и addNewUser
     * @return Возвращает текущий объект AddUserPage, чтобы протестировать поведение с некорректной эл.почтой
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

    public String selectRandomUserType() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", openUserTypes);
        webElementActions.click(openUserTypes);
        List<WebElement> userTypeOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//ul[@class='select2-results']/li")));
        int randomIndex = random.nextInt(userTypeOptions.size());
        WebElement selectedElement = userTypeOptions.get(randomIndex);
        String selectedText = selectedElement.getText();
        selectedElement.click();
        return selectedText;
    }

    public List<String> getAvailableUserTypes() {
        webElementActions.click(openUserTypes);
        List<WebElement> userTypeOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//ul[@class='select2-results']/li")));
        List<String> userTypes = new ArrayList<>();
        for (WebElement userType : userTypeOptions) {
            userTypes.add(userType.getText());
        }
        return userTypes;
    }

    public String selectRandomTimeZone() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", openTimeZone);
        webElementActions.click(openTimeZone);
        List<WebElement> timeZoneOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//ul[@class='select2-results']/li")));
        int randomIndex = random.nextInt(timeZoneOptions.size());
        WebElement selectedElement = timeZoneOptions.get(randomIndex);
        String selectedZone = selectedElement.getText();
        selectedElement.click();
        return selectedZone;
    }

    public List<String> getAvailableTimeZones() {
        webElementActions.click(openTimeZone);
        List<WebElement> timeZoneOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//ul[@class='select2-results']/li")));
        List<String> timeZones = new ArrayList<>();
        for (WebElement timeZone : timeZoneOptions) {
            timeZones.add(timeZone.getText());
        }
        return timeZones;
    }

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

    // проверяет наличие заголовка или одного из элементов страницы
    public boolean isPageLoaded() {
        return addUserBtn.isDisplayed();
    }
}
