package com.digital_nomads.talent_lms.page.dashboard;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Category;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.category.AddCategoryPage;
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

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUserBtn;

    @FindBy(xpath = "//span[@class='arrow-down']")
    public WebElement subMenu;

    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    public WebElement legacyMenuItem;

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

    @FindBy(xpath = "//a[normalize-space()='Add category']")
    public WebElement addCategoryBtn;
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    public AddCategoryPage addNewCategory(Category category){
        webElementActions.click(addCategoryBtn);
        webElementActions.sendKeys(addCategoryPage.categoryName, category.getCatName())
//                .randomClick(addCategoryPage.selectorOfParentCategory)
//                .randomClick(addCategoryPage.priceSetter)
                .click(addCategoryPage.addCategoryBtn);
        return new AddCategoryPage();
    }

}
