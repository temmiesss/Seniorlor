package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.entity.Sections;
import com.digital_nomads.talent_lms.entity.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;

/**
 * @author Akylai
 */
public class UserPage extends BasePage {
    private DashboardPage dashboardPage = new DashboardPage();
    private Sections sections = new Sections();

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUser;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='surname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//input[@name='login']")
    public WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

//    @FindBy(xpath = "//textarea[@name='description']")
//    public WebElement bioInput;

//    @FindBy(xpath = "//div[@class='select2-container tl-select2 select2-dropdown-open select2-container-active']")
//    public WebElement userTypesSelect;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addUserBtn;

//    @FindBy(xpath = "//input[@type='checkbox']/parent::td")
//    public WebElement checkboxOn;
//
//    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
//    public WebElement massActionsBtn;
//
//    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
//    public WebElement deleteUser;

    /**
     * Добавляем нового пользователя с корректными данными:
     * @param user Заполняет поля (имя, фамилия, имя пользователя, пароль)
     * @param email Заполняет эл.почту
     * @return Возвращает новый объект UserPage, чтобы продолжить взаимодействие с этой страницей
     */
    public UserPage addNewUser(User user, String email) {
        webElementActions.click(dashboardPage.selectSection(sections.getUsers())).click(this.addUser)
                .sendKeys(this.firstName, user.getFirstName())
                .sendKeys(this.lastName, user.getLastName())
                .sendKeys(this.email, email)
                .sendKeys(this.userName, user.getUsername())
                .sendKeys(this.password, user.getPassword())
                .click(this.addUserBtn);
        return new UserPage();
    }

    /**
     * Пытаемся добавить пользователя с некорректной эл.почтой
     * @param user Заполняет те же поля, что и addNewUser
     * @param email Заполняет эл.почту
     * @return Возвращает текущий объект UserPage, чтобы протестировать поведение с некорректной эл.почтой
     */
    public UserPage addNewUserWithIncorrectEmail(User user, String email) {
        webElementActions.sendKeys(this.firstName, user.getFirstName())
                .sendKeys(this.lastName, user.getLastName())
                .sendKeys(this.email, email)
                .sendKeys(this.userName, user.getUsername())
                .sendKeys(this.password, user.getPassword())
                .click(addUserBtn);
        return this;
    }
}
