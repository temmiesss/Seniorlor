package com.digital_nomads.talent_lms.page.dashboard;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.courses.AddCoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.AddUserPage;

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

    /**
     * @author Akylai
     * Метод открывает меню и переключается на  Legacy Interface
     * @return Возвращает объект типа LoginPage, позволяя продолжить работу с этой страницей.
     */
    public LoginPage switchToLegacyInterface(){
        try{
            webElementActions.click(subMenu).click(legacyMenuItem);
            return new LoginPage();
        } catch(NoSuchElementException e){
            return new LoginPage();
        }
    }

    /**
     * @author Akylai
     * @param section указывает на определенный раздел на странице
     * @return Возвращает элемент, который соответствует переданному названию раздела.
     */
    public WebElement selectSection(String section){
        List<WebElement> allSections = Driver.getDriver().findElements(By.xpath("//div[@class='tl-bold-link']/a"));
        List<String> sectionNames = new ArrayList<>();
        for (WebElement sectionName : allSections){
            sectionNames.add(sectionName.getText());
        }
        WebElement selectedSection = Driver.getDriver().findElement(By.xpath("//div[@class='tl-bold-link']/a[contains(text(),'" + section + "')]"));
        return selectedSection;
    }


    public AddUserPage addNewUser(User user){
        webElementActions.click(addUserBtn);
        webElementActions.sendKeys(addUserPage.firstName, user.getFirstName())
                .sendKeys(addUserPage.lastName, user.getLastName())
                .sendKeys(addUserPage.login, user.getUsername())
                .sendKeys(addUserPage.email, user.getEmail())
                .sendKeys(addUserPage.password, user.getPassword())
                .click(addUserBtn);
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




}
