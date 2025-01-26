package page.dashboard;

import drivers.Driver;
import entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;
import page.login.LoginPage;
import page.users.AddUserPage;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    AddUserPage addUserPage = new AddUserPage();

    @FindBy(xpath = "(//a[normalize-space()='Add user'])[2]")
    public WebElement addUserBtn;

    @FindBy(xpath = "//span[@class='arrow-down']")
    public WebElement subMenu;

    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    public WebElement legacyMenuItem;

    public LoginPage switchToLegacyInterface(){
        try{
            webElementActions.click(subMenu).click(legacyMenuItem);
            return new LoginPage();
        } catch(NoSuchElementException e){
            return new LoginPage();
        }
    }

    public WebElement selectSection(String section){
        List<WebElement> allSections = Driver.getDriver().findElements(By.xpath("//div[@class='tl-bold-link']/a"));
        List<String> sectionNames = new ArrayList<>();
        for (WebElement sectionName : allSections){
            sectionNames.add(sectionName.getText());
        }
        WebElement selectedSection = Driver.getDriver().findElement(By.xpath("//div[@class='tl-bold-link']/a[contains(text(),'" + section + "']"));
        return selectedSection;
    }


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
