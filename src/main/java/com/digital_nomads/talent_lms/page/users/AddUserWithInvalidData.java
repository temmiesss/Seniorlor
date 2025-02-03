package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Akylai
 */
public class AddUserWithInvalidData extends BasePage {

    @FindBy(xpath = "//input[@name='email']/../../span/span")
    public WebElement invalidEmailInputMessage;

    @FindBy(xpath = "//input[@name='name']/../../span/span")
    public WebElement firstNameIsRequiredMessage;

    @FindBy(xpath = "//input[@name='surname']/../../span/span")
    public WebElement lastNameMaxLengthMessage;

    @FindBy(xpath = "//input[@name='email']/../../span/span")
    public WebElement uniqueEmailInputMessage;

    public String getInvalidEmailMessage() {
        return invalidEmailInputMessage.getText();
    }

    public String getFirstNameIsRequiredMessage(){
        return firstNameIsRequiredMessage.getText();
    }

    public String getLastNameMaxLengthMessage(){
        return lastNameMaxLengthMessage.getText();
    }

    public WebElement getPasswordValidationMessage(){
        return driver.findElement(By.xpath("//input[@name='password']/../../span/span"));
    }

    public String getUniqueEmailMessage(){
        return uniqueEmailInputMessage.getText();
    }
}
