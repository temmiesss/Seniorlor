package com.digital_nomads.talent_lms.page.dashboard;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.enums.DashboardSections;
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
import java.util.concurrent.ThreadLocalRandom;

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
     * Переход к заданному разделу на странице Dashboard.
     * @param section Раздел из DashboardSection
     */
    public void navigateToSection(DashboardSections section) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(section.getXpath())));
        element.click();
    }
    /**
     * Переход к случайному разделу Dashboard.
     */
    public void navigateToRandomSection() {
        DashboardSections randomSection = getRandomSection();
        navigateToSection(randomSection);
    }

    /**
     * Получение случайного раздела на странице Dashboard.
     * @return Возвращает рандомный раздел из DashboardSections
     */
    private DashboardSections getRandomSection() {
        DashboardSections[] sections = DashboardSections.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(sections.length);
        return sections[randomIndex];
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
