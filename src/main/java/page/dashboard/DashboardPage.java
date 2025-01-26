package page.dashboard;

import entity.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;
import page.users.AddUserPage;

public class DashboardPage extends BasePage {

    AddUserPage addUserPage = new AddUserPage();
    @FindBy(xpath = "(//a[normalize-space()='Add user'])[2]")
    public WebElement addUserBtn;


    public AddUserPage addNewUser(User user){
        webElementActions.click(addUserBtn);
        webElementActions.sendKeys(addUserPage.firstName, user.getFirstName())
                .sendKeys(addUserPage.lastName, user.getLastName())
                .sendKeys(addUserPage.login, user.getUsername())
                .sendKeys(addUserPage.email, user.getEmail())
                .sendKeys(addUserPage.password, user.getPassword())
                .click(addUserBtn);
        return new AddUserPage();

    }
}
