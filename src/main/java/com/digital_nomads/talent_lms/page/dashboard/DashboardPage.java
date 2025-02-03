package com.digital_nomads.talent_lms.page.dashboard;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Category;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.category.AddCategoryPage;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.enums.Role;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.page.courses.AddCoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.AddUserPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage extends BasePage {

    AddCoursePage addCoursePage = new AddCoursePage();

    @FindBy(xpath = "//div[@class='hidden-phone']/a[normalize-space()='Add user']")
    public WebElement addUserBtn;

    @FindBy(xpath = "//span[@class='arrow-down']")
    public WebElement subMenu;

    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    public WebElement legacyMenuItem;

    @FindBy(xpath = " //*[@id=\"tl-admin-courses\"]/div/div[2]/a[1]")
    public WebElement addCourseBtn;

    @FindBy(css = "#tl-dropdown-roles")
    public WebElement dropdownRoles;

    @FindBy(xpath = "//div[@class='tl-bold-link']")
    public WebElement allSections;

    /**
     * @return Возвращает объект типа LoginPage, позволяя продолжить работу с этой страницей.
     * @author Akylai
     * Метод ожидает и открывает меню и переключается на  Legacy Interface
     */
    public LoginPage switchToLegacyInterface() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(subMenu));
            webElementActions.click(subMenu);

            wait.until(ExpectedConditions.visibilityOf(legacyMenuItem));
            webElementActions.click(legacyMenuItem);

            return new LoginPage();
        } catch (NoSuchElementException e) {
            System.err.println("Element not found or not clickable: " + e.getMessage());
            return new LoginPage();
        }
    }

    public DashboardPage selectSection(Section section){
        webElementActions.click(allSections.findElement(By.xpath("//a[contains(text(),'" + section + "')]")));
        return new DashboardPage();
    }

    // Метод для поиска раздела по enum Section
    public WebElement findSectionByName(Section section) {
        // Получаем все дочерние элементы из allSections
        List<WebElement> sections = allSections.findElements(By.xpath("./a"));
        for (WebElement sec : sections) {
            if (sec.getText().equalsIgnoreCase(section.getSectionName())) {
                return sec;
            }
        }
        throw new IllegalArgumentException("Section not found: " + section.getSectionName());
    }

    /**
     * @return Переход к добавлению пользователя со страницы dashboard в AddUserPage
     * @author Akylai
     */
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUserBtn);
        return new AddUserPage();
    }

    @FindBy(xpath = "//a[normalize-space()='Add category']")
    public WebElement addCategoryBtn;
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    public AddCategoryPage addNewCategory(Category category){
        webElementActions.click(addCategoryBtn);
        webElementActions.sendKeys(addCategoryPage.categoryName, category.getCatName())
//                .click(addCategoryPage.selectorOfParentCategory)
//                .click(addCategoryPage.priceSetter)
                .click(addCategoryPage.addCategoryBtn);
        return new AddCategoryPage();
    }

    /**
     * @return Возвращает объект типа AddCoursePage, позволяя продолжить работу с этой страницей.
     * @author Gera
     * Метод нажимает на Add course и открывает страницу Add course(create)
     */

    public AddCoursePage addNewCourse(Course course) {
        webElementActions.click(addCourseBtn);
        webElementActions.sendKeys(addCoursePage.courseName, course.getCourseName())
                .click(addCoursePage.category)
                .click(addCoursePage.select2)
                .sendKeys(addCoursePage.description, course.getDescription()).
                click(addCoursePage.courseCodeBtn)
                .sendKeys(addCoursePage.courseCode, course.getCourseCode())
                .click(addCoursePage.priceBtn)
                .sendKeys(addCoursePage.price, course.getPrice())
                .click(addCoursePage.videoBtn)
                .sendKeys(addCoursePage.video, course.getVideo())
                .click(addCoursePage.capacityBtn)
                .sendKeys(addCoursePage.capacity, course.getCapacity())
                .click(addCoursePage.submit);
        return new AddCoursePage();
    }

    public DashboardPage navigateToRole(Role role) {
        webElementActions.click(dropdownRoles)
                .click(dropdownRoles.findElement(By.xpath("//a[normalize-space()='" + role.getRole() + "']")));
        return new DashboardPage();
    }
}