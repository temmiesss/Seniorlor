package com.digital_nomads.talent_lms.page.dashboard;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.enums.Role;
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
import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    AddUserPage addUserPage = new AddUserPage();
    AddCoursePage addCoursePage = new AddCoursePage();

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUserBtn;

    @FindBy(xpath = "//span[@class='arrow-down']")
    public WebElement subMenu;

    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    public WebElement legacyMenuItem;


    @FindBy(xpath = " //*[@id=\"tl-admin-courses\"]/div/div[2]/a[1]")
    public WebElement addCourseBtn;

    @FindBy(css = "#tl-dropdown-roles")
    public WebElement dropdownRoles;
    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Users']")
    public WebElement usersSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Courses']")
    public WebElement coursesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Categories']")
    public WebElement categoriesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Groups']")
    public WebElement groupsSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Branches']")
    public WebElement branchesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Events engine']")
    public WebElement eventsEngineSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='User types']")
    public WebElement userTypesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Import - Export']")
    public WebElement importExportSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Reports']")
    public WebElement reportsSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Account & Settings']")
    public WebElement AccountAndSettingsSection;

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
    /**
     * @author Akylai
     */

    public void navigateToSection(String sectionName) {
        WebElement section;
        switch (sectionName.toLowerCase()) {
            case "users":
                section = usersSection;
                break;
            case "courses":
                section = coursesSection;
                break;
            case "categories":
                section = categoriesSection;
                break;
            case "groups":
                section = groupsSection;
                break;
            case "branches":
                section = branchesSection;
                break;
            case "events engine":
                section = eventsEngineSection;
                break;
            case "user types":
                section = userTypesSection;
                break;
            case "import - export":
                section = importExportSection;
                break;
            case "reports":
                section = reportsSection;
                break;
            case "account & settings":
                section = AccountAndSettingsSection;
                break;
            default:
                throw new IllegalArgumentException("Section not found: " + sectionName);
        }
        section.click();
    }

    /**
     * @author Akylai
     * @return Переход к добавлению пользователя со страницы dashboard в AddUserPage
     */
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUserBtn);
        return new AddUserPage();
    }

    /**
     * @author Gera
     * Метод нажимает на Add course и открывает страницу Add course(create)
     * @return Возвращает объект типа AddCoursePage, позволяя продолжить работу с этой страницей.
     */

    public AddCoursePage addNewCourse(Course course){
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

     public DashboardPage navigateToRole(Role role){
        webElementActions.click(dropdownRoles)
               .click(dropdownRoles.findElement(By.xpath("//a[normalize-space()='" + role.getRole() + "']")));
        return new DashboardPage();
     }

}
