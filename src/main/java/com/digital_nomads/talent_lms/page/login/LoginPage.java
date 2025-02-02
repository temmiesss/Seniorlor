package com.digital_nomads.talent_lms.page.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name ='login']")
    public WebElement login;

    @FindBy(xpath = "//input[@name ='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name ='submit']")
    public WebElement submit;

    public DashboardPage doLogin(String username, String password){
        webElementActions.sendKeys(login, username)
                .sendKeys(this.password, password)
                .click(submit);
        return new DashboardPage();



    }
//    public DashBoardReports doLogin1 (String username, String password){
//        webElementActions.sendKeys(login, username)
//                .sendKeys(this.password, password)
//                .click(submit);
//        return new DashBoardReports();
//
//
//
//    }
}
