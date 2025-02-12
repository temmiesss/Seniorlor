import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.eventsEngine.EventsEnginePage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomTextGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class NotificationsTest extends BaseTest{

    EventsEnginePage eventsEnginePage = new EventsEnginePage();
    RandomTextGenerator randomTextGenerator = new RandomTextGenerator();


    @BeforeTest
    public void logIn(){
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"),
                        ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.EVENTS_ENGINE);
    }


    @Test
    public void deleteNotificationTest() {
        String notificationName = eventsEnginePage.getRandomNotification().getNotificationName();
        eventsEnginePage.deleteNotificationByName(notificationName);

        // Проверяем, что строка уведомления больше не отображается
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isNotificationPresent = true;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//tbody/tr[td[normalize-space(text())='" + notificationName + "']]")
            ));
            eventsEnginePage.getNotificationRowByName(notificationName);
        } catch (NoSuchElementException e) {
            isNotificationPresent = false; // Если исключение выброшено, значит уведомления нет
        }

        Assert.assertFalse(isNotificationPresent, "Notification '" + notificationName + "' was not deleted!");
    }

    @Test
    public void editNotificationNameTest(){

        String oldName = eventsEnginePage.getRandomNotification().getNotificationName();
        String newName = randomTextGenerator.randomText1();

        eventsEnginePage.editNotificationName(oldName, newName);
        Assert.assertEquals(eventsEnginePage.notificationUpdateMessage.getText(),"Notification updated successfully", "Notification " + oldName + " was not updated");

    }

    @Test
    public void editEventTest(){
        String name = eventsEnginePage.getRandomNotification().getNotificationName();

        eventsEnginePage.editEvent(name);
        Assert.assertEquals(eventsEnginePage.notificationUpdateMessage.getText(),"Notification updated successfully", "Notification " + name + " was not updated");

    }

    @Test
    public void editRecipientTest(){

        String name = eventsEnginePage.getRandomNotification().getNotificationName();

        eventsEnginePage.editRecipient(name);
        Assert.assertEquals(eventsEnginePage.notificationUpdateMessage.getText(),"Notification updated successfully", "Notification " + name + " was not updated");

    }

    @Test
    public void editNotificationBodyTest(){

        String name = eventsEnginePage.getRandomNotification().getNotificationName();

        eventsEnginePage.editNotificationBody(name);
        Assert.assertEquals(eventsEnginePage.notificationUpdateMessage.getText(),"Notification updated successfully", "Notification " + name + " was not updated");

    }


}
