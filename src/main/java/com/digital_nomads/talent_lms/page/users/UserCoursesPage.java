package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


/**
 * @author Akylai
 */
public class UserCoursesPage extends BasePage {

    @FindBy(xpath = "//i[@class='icon-plus icon-grid tl-toggle-user']")
    public WebElement enrollIcon;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    public WebElement massActionBTN;

    @FindBy(xpath = "//div[@class='btn-group open']/ul")
    public WebElement actionsDropdownMenu;

    @FindBy(xpath = "//a[@class='btn tl-mass-assign btn-success']")
    public WebElement submitEnrollActionBtn;

    @FindBy(xpath = "//a[@class='btn btn-danger tl-mass-unassign']")
    public WebElement submitUnenrollActionBtn;

    @FindBy(xpath = "//div[@id='tl-courses-mass-modal']/descendant::a[@class='btn']")
    public WebElement cancelBtn;

    /**
     * выполняет добавление курса пользователю, осуществляя поиск курса по имени и клик по иконке для записи на курс.
     *
     * @param courseName - название выбранного курса
     * @return если курс не был найден или если запись прошла успешно, метод возвращает текущую страницу
     */
    public UserCoursesPage enrollCoursesToUser(String courseName) {
        try {
            WebElement courseElement = driver.findElement(By.xpath("//span[text()='" + courseName + "']"));
            webElementActions.moveToElement(courseElement).click(enrollIcon);
            return new UserCoursesPage();
        } catch (NoSuchElementException e) {
            System.out.println("Course is not found");
        }
        return this;
    }

    /**
     * проверяет, записан ли курс для пользователя, проверяя наличие кнопки отписки (иконки "минус") для указанного курса
     *
     * @param courseName - выбранный курс
     * @return Если элемент с указанным курсом и иконкой отписки не найден на странице, генерируется исключение NoSuchElementException,
     * и метод возвращает false, что означает, что курс не был записан.
     */
    public boolean isCourseEnrolled(String courseName) {
        try {
            WebElement unEnrollElement = driver.findElement(By.xpath("//span[text()='" + courseName +
                    "']/ancestor::tr//i[@class='icon-minus icon-grid tl-toggle-user']"));
            return unEnrollElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Метод аналогичен предыдущему, только в этом случае он проверяет отписан ли курс для пользователя
     *
     * @param courseName выбранный курс
     * @return Если элемент с указанным курсом и иконкой подписки не найден на странице, генерируется исключение NoSuchElementException,
     * и метод возвращает false, что означает, что курс уже записан и отписаться от него нельзя
     */
    public boolean isCourseUnenrolled(String courseName) {
        try {
            WebElement enrollElement = driver.findElement(By.xpath("//span[text()='" + courseName +
                    "']/ancestor::tr//i[@class='icon-plus icon-grid tl-toggle-user']"));
            return enrollElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Выполняет массовое действие с курсами, которое зависит от переданного параметра action
     *
     * @param courseName - выбранный курс
     * @param action     - выбранное действие
     * @return Если передан неизвестный параметр action, выбрасывается исключение IllegalArgumentException.
     */
    public UserCoursesPage performUserCoursesMassAction(String courseName, String action) {
        WebElement courseNameElement = driver.findElement(By.xpath("//span[text()='" + courseName + "']"));
        webElementActions.moveToElement(courseNameElement);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop.fade.in")));

        WebElement userCheckboxElement = driver.findElement(By.xpath("//span[@title='" + courseName +
                "']/ancestor::tr//input[@type='checkbox']"));
        // Клик по чекбоксу через JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", userCheckboxElement);

        massActionBTN.click();
        showMassActionDropdownMenu(action);

        switch (action) {
            case "Enroll":
                webElementActions.click(submitEnrollActionBtn);
                break;
            case "Unenroll":
                webElementActions.click(submitUnenrollActionBtn);
                break;
            case "Reset progress":
                // Не поняла как делать, выходит, что нет курсов для сброса прогресса
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
        return this;
    }

    /**
     * Отображает меню с массовыми действиями и выбор нужного действия, которое будет выполнено.
     * @param action - действие из выпадающего списка
     */
    public void showMassActionDropdownMenu(String action) {
        WebElement actionElement = actionsDropdownMenu.findElement(By.xpath(".//a[normalize-space()='" + action + "']"));
        actionElement.click();
    }

    /**
     * предназначен для получения текста сообщения из всплывающего уведомления на веб-странице.
     * @return возвращает текст сообщения
     */
    public String getAlertMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='toast-message']")));

        // Сохраняем текст через JavaScript (даже если элемент исчезнет)
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String messageText = (String) js.executeScript("return arguments[0].innerText;", messageElement);
        return messageText;
    }


}
