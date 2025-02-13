import com.digital_nomads.talent_lms.entity.Category;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class AddNewCategoryTest extends BaseTest {
    Category category = new Category();

    public AddNewCategoryTest() {}
    @BeforeMethod
    public static void setUp() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

//    @AfterTest
//    public void goBackToDashboardPage(){
//        WebElement goToHome = driver.findElement(By.xpath("//*[@id='tl-dropdown-goto']/ul/li[1]))"));
//        goToHome.click();
//    }

    @Test
    public void addNewCategory() {
        categoryPage.AddNewCategory();
        WebElement isRequired = driver.findElement(By.xpath("//*[@id='toast-container']/div/div"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Success! New category created.");
    }

    /**
     * @author Amal
     *
     * Проверяет что администратор может создать категорию с корректным названием.
     */
    @Test
    public void CategoryNameRequiresFillingNotEmpty() {
        categoryPage.AddNewCategory();
        if (category.getCatName().isEmpty()) {
            WebElement isRequired = driver.findElement(By.xpath("//div/span/span[@class='help-inline']"));
            String actual = isRequired.getText();
            Assert.assertEquals(actual, "'Name' is required");
            throw new IllegalArgumentException("Ошибка: строка пустая.");
        }
    }

    @Test
    public void CategoryNameRequiresFillingNoExceed() {
        categoryPage.AddNewCategory();
        if (category.getCatName().length() > 80) {
            WebElement isRequired = driver.findElement(By.xpath("//div/span/span[@class='help-inline']"));
            String actual = isRequired.getText();
            Assert.assertEquals(actual, "'Name' cannot exceed 80 characters");
            throw new IllegalArgumentException("Ошибка: строка превышает 80 символов.");
        }
    }
    @Test
    public void CategoryPriceRequiresFilling() {
        categoryPage.AddNewCategory();
        if(!category.getPrice().matches(".*[A-Za-z].*")){
            WebElement isRequired = driver.findElement(By.xpath("//*[@id='control-group-price']/div/span/span"));
            String actual = isRequired.getText();
            Assert.assertEquals(actual, "This is not a valid 'Price'");
            throw new IllegalArgumentException("Ошибка: не может содержать текст");
        }
    }

    @Test
    public void CategoriesEditor() {
        dashboardPage.selectSection(Section.CATEGORIES);
        categoryPage.OptionButtonEditor();
        WebElement isRequired = driver.findElement(By.xpath("//div/span[@class='tl-box-title-options']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual,"Home / Categories / ");
    }

    @Test
    public void CategoriesEditeName() {
        dashboardPage.selectSection(Section.CATEGORIES);
        categoryPage.OptionButtonEditor();
        categoryPage.ChangeCategoryName();
        WebElement isRequired = driver.findElement(By.xpath("//*[@id='toast-container']/div/div"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Category updated successfully");

    }

    @Test
    public void CategoriesEditePrice() {
        dashboardPage.selectSection(Section.CATEGORIES);
        categoryPage.OptionButtonEditor();
        categoryPage.ChangeCategoryPrice();
        WebElement isRequired = driver.findElement(By.xpath("//*[@id='toast-container']/div/div"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Category updated successfully");
    }


    @Test
    public void CategoriesAlertDelete() throws InterruptedException {
        dashboardPage.selectSection(Section.CATEGORIES);
        Thread.sleep(3000);
        categoryPage.DeleteAlertInOptionBtn();
        webElementActions.click(categoryPage.alertDeleteBtn);
        Thread.sleep(5000);
        //assert
    }

    @Test
    public void CategoriesAlertCancelBtn() throws InterruptedException {
        dashboardPage.selectSection(Section.CATEGORIES);
        Thread.sleep(3000);
        categoryPage.DeleteAlertInOptionBtn();
        webElementActions.click(categoryPage.alertCancleBtn);

        //assert
    }

    @Test
    public void CategoriesAlertCloseBtn() throws InterruptedException {
        dashboardPage.selectSection(Section.CATEGORIES);
        Thread.sleep(3000);
        categoryPage.DeleteAlertInOptionBtn();
        webElementActions.click(categoryPage.alertCloseBtn);
        //assert
    }

//    @Test
//    public void getListOfCategoryNameTest() throws InterruptedException {
//        dashboardPage.selectSection(Section.CATEGORIES);
//// Вызов метода получения списка всех названий категроий и вывода на консоль
//        List<Category> categoriesNames = categoryPage.getCategoryNameFromTable();
//        for (Category categoryNames : categoriesNames) {
//            System.out.println(categoryNames);
//        }
//        Thread.sleep(5000);
//    }
}
