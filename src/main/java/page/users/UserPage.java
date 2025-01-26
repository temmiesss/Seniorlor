package page.users;

import entity.Sections;
import entity.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;
import page.dashboard.DashboardPage;

public class UserPage extends BasePage {
    private DashboardPage dashboardPage = new DashboardPage();
    private Sections sections = new Sections();

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUserBtn;

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

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement addBtn;

//    @FindBy(xpath = "//input[@type='checkbox']/parent::td")
//    public WebElement checkboxOn;
//
//    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
//    public WebElement massActionsBtn;
//
//    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
//    public WebElement deleteUser;

    public UserPage addNewUser(User user, String email) {
        webElementActions.click(dashboardPage.selectSection(sections.getUsers())).click(this.addUserBtn)
                .sendKeys(this.firstName, user.getFirstName())
                .sendKeys(this.lastName, user.getLastName())
                .sendKeys(this.email, email)
                .sendKeys(this.userName, user.getUsername())
                .sendKeys(this.password, user.getPassword()).click(this.addBtn);
        return new UserPage();
    }

    public UserPage addNewUserIncorrect(User user, String email) {
        webElementActions.sendKeys(this.firstName, user.getFirstName())
                .sendKeys(this.lastName, user.getLastName())
                .sendKeys(this.email, email)
                .sendKeys(this.userName, user.getUsername())
                .sendKeys(this.password, user.getPassword())
                .click(addBtn);
        return this;
    }
}
