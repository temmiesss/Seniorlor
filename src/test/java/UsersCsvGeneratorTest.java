import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Akylai
 */
public class UsersCsvGeneratorTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
    }

    Faker faker = new Faker();

    @Test
    public void generatorCsvTest() throws IOException {
        // Получаем данные из колонок (индексы колонок зависят от структуры таблицы)
        List<String> usernames = sortingUserPage.getColumnData(2);
        List<String> email = sortingUserPage.getColumnData(3);
        List<String> userType = sortingUserPage.getColumnData(4);
        List<String> registration = sortingUserPage.getColumnData(5);
        List<String> lastLogin = sortingUserPage.getColumnData(6);

        // Формируем список строк для CSV
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"User", "Email", "User type", "Registration", "Last login"}); // Заголовки

        for (int i = 0; i < usernames.size(); i++) {
            data.add(new String[]{usernames.get(i), email.get(i), userType.get(i), registration.get(i), lastLogin.get(i)});
        }
        // Генерируем CSV-файл
        String fileName = "users_data.csv";
        csvGenerator.generateCsvFile(fileName, data);

        String filePath = "src/main/resources/files/" + fileName;
        File file = new File(filePath);
        Assert.assertTrue(file.exists(), "Файл CSV не был создан!");

        // Количество строк в файле должно совпадать с количеством данных
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        Assert.assertEquals(fileLines.size(), data.size(), "Количество строк в CSV-файле не совпадает с ожидаемым!");

        // Заголовки в файле должны совпадать с ожидаемыми
        String expectedHeader = String.join(",", data.get(0)); // Ожидаемые заголовки
        String actualHeader = fileLines.get(0); // Заголовки из файла
        Assert.assertEquals(actualHeader, expectedHeader, "Заголовки в CSV-файле не совпадают с ожидаемыми!");

        // Проверяем, что файл скачан в папке загрузок
//        Assert.assertTrue(fileUtils.isFileDownloaded(fileName), "Файл не был скачан!");
//
//        // Сохраняем скачанный файл в папку ресурсов
//        fileUtils.saveDownloadedFile(fileName);

        // Проверяем, что файл теперь существует в папке resources
        File savedFile = new File(filePath);
        Assert.assertTrue(savedFile.exists(), "Файл не был сохранен в папке resources!");

    }
}



