import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.eventsEngine.AddNotificationPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomTextGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddNotificationTest extends BaseTest{
    AddNotificationPage addNotificationPage = new AddNotificationPage();
    RandomTextGenerator randomTextGenerator = new RandomTextGenerator();

    @BeforeTest
    public void logIn(){
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"),
                ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

//    @BeforeMethod
//    public void backToDashboard(){
//        driver.get("https://blackjack.talentlms.com/dashboard");
//    }


    @Test
    public void addNotificationTest(){
        addNotificationPage.addNewNotification();
        Assert.assertEquals(addNotificationPage.succeedMessage.getText(), "Success! New notification created.", "Notification was not created");
    }

    @Test
    public void notificationWithoutName(){
        addNotificationPage
                .openNotificationForm()
                .selectRandomEvent(true)
                .selectRandomRecipient(true)
                .fillNotificationBody(randomTextGenerator.randomText1())
                .createNotification();
        Assert.assertEquals(addNotificationPage.nameRequiredMessage.getText(), "'Name' is required", "");
        Assert.assertEquals(addNotificationPage.checkColour(addNotificationPage.nameRequiredMessage), "#b94a48", "Color is not right");

    }

    @Test
    public void addNotificationWithNameOnly(){
        addNotificationPage
                .openNotificationForm()
                .enterNotificationName(randomTextGenerator.randomText1())
                .createNotification();
        Assert.assertEquals(addNotificationPage.eventRequiredMessage.getText(), "'Event' is required");
        Assert.assertEquals(addNotificationPage.recipientRequiredMessage.getText(), "'Recipient' is required");
    }

    @Test
    public void addNotificationWithoutBody(){
        addNotificationPage
                .openNotificationForm()
                .enterNotificationName(randomTextGenerator.randomText1())
                .selectRandomEvent(true)
                .selectRandomRecipient(true)
                .createNotification();
        Assert.assertEquals(addNotificationPage.bodyRequiredMessage.getText(), "'Body' is required");
        Assert.assertEquals(addNotificationPage.checkColour(addNotificationPage.bodyRequiredMessage), "#262626", "Color is not right");

    }

    @Test
    public void addNotification(){
        addNotificationPage
                .openNotificationForm()
                .cancelCreatingNewNotification();
        Assert.assertEquals(driver.getCurrentUrl(), "https://seniorlor2024.talentlms.com/eventsengine/notification_index", "Redirect after cancel failed!");

    }

}
