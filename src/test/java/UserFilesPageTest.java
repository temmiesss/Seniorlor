import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.annotations.BeforeMethod;

/**
 * @author Akylai
 * Класс не доработан. Возникли трудности со скачиванием файла
 */
public class UserFilesPageTest extends BaseTest{
    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
    }


//    @Test
//    void checkToUploadFile() {
//        String username = "mary";
//        editUserDataPage.openUserProfile(username);
//        userFilesPage = editUserDataPage.clickFiles();
//
//        // Загружаем файл
//        userFilesPage.clickCloudBtn();
//
//        // Даем время для загрузки
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfAllElements(userFilesPage.allFilesInTable));
//
//        // Проверяем, что файл загрузился
//        List<String> allFilesText = new ArrayList<>();
//        for (WebElement el : userFilesPage.allFilesInTable) {
//            allFilesText.add(el.getText());
//        }
//
//        // Проверка, что файл появился в списке
//        Assert.assertTrue(allFilesText.contains("minyon.jpg"), "File should be uploaded and displayed in the list");
//    }
}
