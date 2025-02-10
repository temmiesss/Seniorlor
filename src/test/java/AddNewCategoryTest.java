import com.digital_nomads.talent_lms.entity.Category;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AddNewCategoryTest extends BaseTest {
    Category category = new Category();

    /**
    @author Amal

     Проверяет что администратор может создать категорию с корректным названием.
     */
    @Test
    public void CategoryNameRequiresFilling() {
        driver.get("https://sololeveling.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        categoryPage.AddNewCategory();
        if (category.getCatName().isEmpty()) {
            WebElement isRequired = driver.findElement(By.xpath("//div/span/span[@class='help-inline']"));
            String actual = isRequired.getText();
            Assert.assertEquals(actual, "'Name' is required");
            categoryPage.CancelCategoryBtn();
            throw new IllegalArgumentException("Ошибка: строка пустая.");
        }
        else if (category.getCatName().length() > 80) {
            WebElement isRequired = driver.findElement(By.xpath("//div/span/span[@class='help-inline']"));
            String actual = isRequired.getText();
            Assert.assertEquals(actual, "'Name' cannot exceed 80 characters");
            categoryPage.CancelCategoryBtn();
            throw new IllegalArgumentException("Ошибка: строка превышает 80 символов.");
        }
    }
}
