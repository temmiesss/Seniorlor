package com.digital_nomads.talent_lms.page.users;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;

/**
 * @author Akylai
 */
public class UserPage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUser;

    //Метод переводит на следующую страницу для регистрации пользователя
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUser);
        return new AddUserPage();
    }

}
