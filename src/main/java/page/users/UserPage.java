package page.users;

import drivers.Driver;
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

    @FindBy(xpath = "//div[@id='tl-users-grid_wrapper']//tbody/tr")
    public WebElement usersInTable;

    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUser);
        return new AddUserPage();
    }

    public boolean isUserPresent(String username) {
        List<WebElement> userRows = Driver.getDriver().findElements(By.xpath("//table[@id='users-table']//td[contains(text(),'" + username + "')]"));
        return !userRows.isEmpty();
    }

}
