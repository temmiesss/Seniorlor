package com.digital_nomads.talent_lms.page.dashboard;

import com.digital_nomads.talent_lms.drivers.Driver;
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

    @FindBy(css = "#tl-admin-dashboard")
    public WebElement dashboardContainer;

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

    public void selectSection(Section section) {
        String sectionName;
        switch (section) {
            case USERS:
                sectionName = Section.USERS.getSectionName();
                break;
            case COURSES:
                sectionName = Section.COURSES.getSectionName();
                break;
            case CATEGORIES:
                sectionName = Section.CATEGORIES.getSectionName();
                break;
            case GROUPS:
                sectionName = Section.GROUPS.getSectionName();
                break;
            case BRANCHES:
                sectionName = Section.BRANCHES.getSectionName();
                break;
            case EVENTS_ENGINE:
                sectionName = Section.EVENTS_ENGINE.getSectionName();
                break;
            case USER_TYPES:
                sectionName = Section.USER_TYPES.getSectionName();
                break;
            case IMPORT_EXPORT:
                sectionName = Section.IMPORT_EXPORT.getSectionName();
                break;
            case REPORTS:
                sectionName = Section.REPORTS.getSectionName();
                break;
            case ACCOUNT_SETTINGS:
                sectionName = Section.ACCOUNT_SETTINGS.getSectionName();
                break;
            default:
                throw new IllegalArgumentException("Section not found: " + section);
        }
        WebElement sectionElement = wait.until(ExpectedConditions.elementToBeClickable(
                dashboardContainer.findElement(By.xpath(".//div[@class='tl-bold-link']/a[normalize-space()='" + sectionName + "']"))));
        sectionElement.click();
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
