import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.course.CVSGenerator;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVGeneratorTest extends BaseTest{

    @BeforeMethod
    public void setUp() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();
        dashboardPage.selectSection(Section.COURSES);
    }

        Faker faker = new Faker();

        @Test
        public void generatorCsvTest() {
            List<String[]> data = List.of(
                    new String[]{"Имя", "Возраст", "Город"},
                    new String[]{"Анна", "28", "Москва"},
                    new String[]{"Иван", "34", "Санкт-Петербург"},
                    new String[]{"Ольга", "25", "Новосибирск"}
            );

//            CVSGenerator.CsvGenerator(faker.name() + ".csv", data);
        }

        @Test
    public void saveCSVTest() throws IOException {
            csvGenerator.saveFile();
            java.io.File csvFile = new File("C:\\Users\\tokto\\Downloads\\ranoprojectgroup-courses (6).xlsx");
            fileUtils.saveToResources(csvFile,"ranoprojectgroup-courses(6).xlsx");
        }

    }

