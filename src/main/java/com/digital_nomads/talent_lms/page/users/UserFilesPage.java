package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * @author Akylai
 * Класс не доработан. Возникли трудности.
 */
public class UserFilesPage extends BasePage {

    @FindBy(xpath = "//span[@id='fileinput-button']")
    public WebElement fileUploadInput;

    public String newUploadFile;

    @FindBy(xpath = "//span[@class='tl-filename']//span")
    public List<WebElement> allFilesInTable;

    @FindBy(xpath = "//input[@id='renameFileName']")
    public WebElement renameFileName;

    @FindBy(xpath = "//input[@value='Update']")
    public WebElement updateInput;

    public UserFilesPage clickCloudBtn() {
        // Используем JavaScript для клка на кнопку загрузки файла
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", fileUploadInput);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(fileUploadInput));

        // Передаем путь к файлу
        String newUploadFile = "C:\\Users\\User\\Seniorlor\\src\\main\\resources\\files\\minyon.jpg";
        fileUploadInput.sendKeys(newUploadFile);  // Прямо передаем путь к файлу

        return this;
    }

    public UserFilesPage pause() {
        webElementActions.pause(3000);
        return this;
    }



}
