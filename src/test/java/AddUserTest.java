import entity.User;
import fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddUserTest extends BaseTest{
    User randomUser = randomUserGenerator.randomUser();
    String email = randomUser.getEmail();

    @Test(priority = 1)
    public void addNewUserTest(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        userPage.addNewUser(randomUser, email);
    }

    @Test(priority = 2)
    public void negativeTest(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/user/create");
        userPage.addNewUserIncorrect(randomUser,"wrong.ru");
        WebElement isRequired = driver.findElement(By.xpath("//div[@class='span8']/child::*[3]//span[@class='help-block']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual,"This is not a valid 'Email address'");
    }
}
