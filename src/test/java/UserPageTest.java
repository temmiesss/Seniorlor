import entity.User;
import fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Akylai
 *
 */

public class UserPageTest extends BaseTest{
    User randomUser = randomUserGenerator.randomUser();
    String email = randomUser.getEmail();

    /**
     * Проверяет, что новый пользователь с корректными данными добавляется успешно.
     */
    @Test(priority = 1)
    public void addNewUserTest(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        userPage.addNewUser(randomUser, email);
    }

    /**
     * Проверяет, что система не позволяет добавить пользователя с некорректным email.
     */
    @Test(priority = 2)
    public void negativeTest(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/user/create");
        userPage.addNewUserWithIncorrectEmail(randomUser,"wrong.ru");
        WebElement isRequired = driver.findElement(By.xpath("//div[@class='span8']/child::*[3]//span[@class='help-block']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual,"This is not a valid 'Email address'");
    }
}
