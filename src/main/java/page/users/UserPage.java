package page.users;

import drivers.Driver;
import entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;
import java.util.List;

/**
 * @author Akylai
 */
public class UserPage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUser;

    //Метод переводит на следующую страницу для регистрации пользователя
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUser);
        return new AddUserPage();
    }

}
