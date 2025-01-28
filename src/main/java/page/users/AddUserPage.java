package page.users;

import entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.BasePage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Akylai
 */
public class AddUserPage extends BasePage {
    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement bioInput;

    @FindBy(xpath = "//span[text()='Learner-Type']/ancestor::a")
    public WebElement openUserTypes;

    @FindBy(xpath = "//span[contains(text(),'Greenwich')]/parent::a")
    public WebElement openTimeZone;

    @FindBy(xpath = "//span[contains(text(),'English')]/parent::a")
    public WebElement setLanguage;

    @FindBy(xpath = "//input[@name='restrict_email']")
    public WebElement excludeFromEmailTick;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserBtn;

    @FindBy(xpath = "//a[text()='cancel']")
    public WebElement cancelAddUserBtn;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement addUserSuccessMessage;

    @FindBy(xpath = "//input[@name='email']/../../span/span")
    public WebElement emailMessage;

    @FindBy(xpath = "//input[@name='login']/../../span/span")
    public WebElement userNameMessage;

    @FindBy(xpath = "//input[@name='name']/../../span/span")
    public WebElement nameIsRequiredMessage;

    @FindBy(xpath = "//input[@name='surname']/../../span/span")
    public WebElement lastNameIsRequiredMessage;

    @FindBy(xpath = "//input[@name='email']/../../span/span")
    public WebElement emailValidationMessage;

    @FindBy(xpath = "//input[@name='password']/../../span/span")
    public WebElement passwordValidationMessage;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserButton;

    @FindBy(xpath = "//input[@type='checkbox']/parent::td")
    public WebElement checkboxOn;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    public WebElement massActionsBtn;

    @FindBy(xpath = "//a[normalize-space()='Activate']")
    public WebElement activateUser;

    @FindBy(xpath = "//a[normalize-space()='Deactivate']")
    public WebElement deactivateUser;

    @FindBy(xpath = "//a[normalize-space()='Delete']")
    public WebElement deleteUser;

    @FindBy(xpath = "//a[@title='Users']")
    public WebElement goBackToUsersSection;


    public UserPage addNewUser(User user) {
        webElementActions.sendKeys(firstName, user.getFirstName())
                .sendKeys(lastName, user.getLastName())
                .sendKeys(login, user.getUsername())
                .sendKeys(email, user.getEmail())
                .sendKeys(password, "QwerTy123%$")
                .click(addUserButton);
        return new UserPage();
    }

    // проверяет наличие заголовка или одного из элементов страницы
    public boolean isPageLoaded() {
        return goBackToUsersSection.isDisplayed();
    }

    /**
     * Добавление пользователя с некорректной эл.почтой
     * @param user Заполняет те же поля, что и addNewUser
     * @return Возвращает текущий объект AddUserPage, чтобы протестировать поведение с некорректной эл.почтой
     */
    public AddUserPage addNewUserWithInvalidEmail(User user, String invalidEmail) {
        webElementActions.sendKeys(firstName, user.getFirstName())
                .sendKeys(lastName, user.getLastName())
                .sendKeys(email, invalidEmail)
                .sendKeys(login, user.getUsername())
                .sendKeys(password, user.getPassword())
                .click(addUserButton);
        return this;
    }

    public AddUserPage selectUserType() {
        webElementActions.click(openUserTypes);
        List<WebElement> userTypeDropDown = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//div[@id='select2-drop']/ul/li")));
        int randomIndex = random.nextInt(userTypeDropDown.size());
        userTypeDropDown.get(randomIndex).click();
        return this;
    }

    public String getSelectedUserType() {
        return openUserTypes.getText();
    }

    public List<String> getAvailableUserTypes() {
        List<WebElement> userTypeDropDown = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@id='select2-drop']/ul/li")));
        return userTypeDropDown.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public AddUserPage selectTimeZone(){
        webElementActions.click(openTimeZone);
        List<WebElement> timeZoneDropdown = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//div[@id='select2-drop']/ul/li")));
        int randomIndex = random.nextInt(timeZoneDropdown.size());
        timeZoneDropdown.get(randomIndex).click();
        return this;
    }
    // Возвращает текст выбранной временной зоны
    public String getSelectedTimeZone() {
        return openTimeZone.getText();
    }

    public List<String> getAvailableTimeZones() {
        List<WebElement> timeZoneDropdown = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@id='select2-drop']/ul/li")));
        return timeZoneDropdown.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    public AddUserPage selectLanguage(){
        webElementActions.click(setLanguage);
        List<WebElement> languageDropdown = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//div[@id='select2-drop']/ul/li")));
        int randomIndex = random.nextInt(languageDropdown.size());
        languageDropdown.get(randomIndex).click();
        return this;
    }

    public AddUserPage clickOnExcludeFromEmailCheckbox(){
        webElementActions.scrollToElement(excludeFromEmailTick).click(excludeFromEmailTick);
        return this;
    }

    public AddUserPage clickOnAddUserSubmitBtn(){
        webElementActions.scrollToElement(activateUser).click(activateUser)
                .scrollToElement(addUserBtn).click(addUserBtn);
        return this;
    }

    public String getSuccessMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div{@class='toast-message']/a")));
        return messageElement.getText();
    }

    public UserPage cancelAddUser() {
        webElementActions.click(cancelAddUserBtn);
        return new UserPage(); // После отмены пользователь возвращается на UserPage
    }
}

