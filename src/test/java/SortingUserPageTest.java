import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Akylai
 */
public class SortingUserPageTest extends BaseTest{

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testSortingByUsername() throws IOException {
        sortingUserPage.sortByColumn("User");
        List<String> usernameAfter = sortingUserPage.getColumnData(2);

        Assert.assertTrue(sortingUserPage.isSorted(usernameAfter), "Usernames are not sorted correctly");
    }

    @Test
    public void testSortingByEmail() {
        sortingUserPage.sortByColumn("Email");
        List<String> emailAfter = sortingUserPage.getColumnData(3);
        Assert.assertTrue(sortingUserPage.isSorted(emailAfter), "Sorting is not correct");
    }

    @Test
    public void testSortingByUserType() {
        sortingUserPage.sortByColumn("User type");
        List<String> userTypeAfter = sortingUserPage.getColumnData(4);

        System.out.println("Extracted User Types: " + userTypeAfter);
        Assert.assertTrue(sortingUserPage.isSortedByUserType(userTypeAfter), "User types are not sorted correctly");
    }

    @Test
    public void testSortingByRegistration() {
        sortingUserPage.sortByColumn("Registration");
        List<String> registrationAfter = sortingUserPage.getColumnData(5);
        Assert.assertTrue(sortingUserPage.isSortedByRegistrationDate(registrationAfter), "Registration is not sorted correctly");
    }

    @Test
    public void testSortingByLastLogin() {
        sortingUserPage.sortByColumn("Last login");
        List<String> lastLoginAfter = sortingUserPage.getColumnData(6);
        Assert.assertTrue(sortingUserPage.isSortedByLastLogin(lastLoginAfter), "Last login is not sorted correctly");
    }

    @Test
    public void testSortingByUsernameAndDownloadCsv() throws IOException, InterruptedException {
        // Сортируем таблицу по столбцу "User"
        sortingUserPage.sortByColumn("User");
        List<String> usernameAfter = sortingUserPage.getColumnData(2);

        // Проверяем, что сортировка прошла успешно
        Assert.assertTrue(sortingUserPage.isSorted(usernameAfter), "Usernames are not sorted correctly");

        // Экспортируем CSV-файл
        sortingUserPage.exportCSV();

        // Указываем имя загружаемого файла
        String fileName = "seniorlor-users.csv";

        // Ожидаем загрузки файла
        Thread.sleep(5000);

        // Проверяем, что файл появился в папке загрузок
        File downloadedFile = new File("C:\\Users\\User\\Downloads\\" + fileName);
        Assert.assertTrue(downloadedFile.exists(), "Файл не найден в папке загрузок!");

        // Переносим файл в resources
        fileUtils.saveToResources(downloadedFile, fileName);

        // Проверяем, что файл успешно скопирован
        File savedFile = new File("src/main/resources/files/" + fileName);
        Assert.assertTrue(savedFile.exists(), "Файл не был скопирован в папку resources!");
    }
}
