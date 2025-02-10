package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Akylai
 * Методы изменения данных пользователя
 */
public class EditUserDataPage extends BasePage {
    public String progressUrl;
    public String infographicUrl;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='name']")
    public WebElement editFirstNameField;

    @FindBy(xpath = "//div[@class='input-append tl-countdown']/input[@type='text' and @name='surname']")
    public WebElement editLastNameField;

    @FindBy(xpath = "//select[@name='acl_user_type_id']/../div/a/span[@class='select2-chosen']")
    public WebElement editUserTypeField;

    @FindBy(css = "div.select2-drop.select2-display-none.select2-drop-auto-width.select2-drop-active > ul")
    public WebElement dropdownList;

    @FindBy(xpath = "//select[@name='timezone']/../div/a/span[@class='select2-chosen']")
    public WebElement editTimeZoneField;

    @FindBy(xpath = "//select[@name='language']/../div/a/span[@class='select2-chosen']")
    public WebElement editLanguageField;

    @FindBy(xpath = "//input[@name='submit_personal_details']")
    public WebElement updateUserBTN;

    @FindBy(xpath = "//a[text()='Progress']")
    public WebElement progressBtn;

    @FindBy(xpath = "//a[text()='Infographic']")
    public WebElement infographicsBtn;

    @FindBy(xpath = "//a[text()='Courses']")
    public WebElement courses;

    @FindBy(xpath = "//a[text()='Groups']")
    public WebElement groups;

//    @FindBy(xpath = "//a[text()='Branches']")
//    public WebElement branches;

    @FindBy(xpath = "//a[text()='Files']")
    public WebElement files;

    /**
     * Метод находит элемент с именем пользователя на веб-странице
     *
     * @param username - имя пользователя, которое принимает метод в параметр
     */
    public void openUserProfile(String username) {
        WebElement userElement = driver.findElement(By.xpath("//span[@title='" + username + "']"));
        webElementActions.click(userElement);
    }

    /**
     * Редактирует данные пользователя в зависимости от переданного типа поля
     *
     * @param fieldType выбирает поле, которое нужно редактировать
     * @param value     подставляет значение на которое нужно заменить имеющееся поле
     * @return Возвращает обновленную страницу EditUserDataPage
     */
    public EditUserDataPage editUserData(String fieldType, String value) {
        WebElement fieldToEdit;
        switch (fieldType.toLowerCase()) {
            case "firstname":
                fieldToEdit = editFirstNameField;
                fieldToEdit.clear();
                webElementActions.click(fieldToEdit)
                        .sendKeys(fieldToEdit, value);
                break;
            case "lastname":
                fieldToEdit = editLastNameField;
                fieldToEdit.clear();
                webElementActions.click(fieldToEdit)
                        .sendKeys(fieldToEdit, value);
                break;
            case "usertype":
                fieldToEdit = editUserTypeField;
                webElementActions.click(fieldToEdit);
                WebElement userTypeOption = dropdownList.findElement(By.xpath(".//li/div[text()='" + value + "']"));
                webElementActions.click(userTypeOption);
                break;
            case "timezone":
                fieldToEdit = editTimeZoneField;
                webElementActions.click(fieldToEdit);
                WebElement timeZoneOption = dropdownList.findElement(By.xpath(".//li/div[text()='" + value + "']"));
                webElementActions.click(timeZoneOption);
                break;
            case "language":
                fieldToEdit = editLanguageField;
                webElementActions.click(fieldToEdit);
                WebElement languageOption = dropdownList.findElement(By.xpath(".//li/div[text()='" + value + "']"));
                webElementActions.click(languageOption);
                break;
            default:
                throw new IllegalArgumentException("Некорректный тип поля: " + fieldType);
        }
        webElementActions.click(updateUserBTN);
        return this;
    }

    /**
     * Геттеры для обновленных полей
     *
     * @return Возвращают элементы поля для обновленных данных
     */
    public WebElement getUpdatedFirstNameField() {
        return editFirstNameField;
    }

    public WebElement getUpdatedLastNameField() {
        return editLastNameField;
    }

    public WebElement getUpdatedUserTypeField() {
        return editUserTypeField;
    }

    public WebElement getUpdateTimeZoneField() {
        return editTimeZoneField;
    }

    public WebElement getUpdatedLanguageField() {
        return editLanguageField;
    }

    /**
     * Проверяет загружена ли страница редактирования
     *
     * @return Если элемент формы редактирования найден, то возвращает true,
     * если нет или возникает исключение - false
     */
    public boolean isEditPageLoaded() {
        try {
            WebElement editForm = driver.findElement(By.xpath("//input[@name='submit_personal_details']"));
            return editForm.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Кликает по кнопке, открывает соответствующую вкладку, сохраняет её URL,
     * а затем возвращается на предыдущую страницу.
     *
     * @return возвращает текущую страницу редактирования пользователя
     */
    public EditUserDataPage clickToProgressTab() {
        webElementActions.pause(2000);
        webElementActions.jsClick(progressBtn);
        webElementActions.pause(1000);
        progressUrl = Driver.getDriver().getCurrentUrl();
        webElementActions.pause(1000);
        webElementActions.navigateBack();
        return this;
    }

    /**
     * Кликает по кнопке инфографики, сохраняет её URL
     *
     * @return возвращается на предыдущую страницу.
     */
    public EditUserDataPage clickToInfographicTab() {
        webElementActions.pause(2000);
        webElementActions.jsClick(infographicsBtn);
        webElementActions.pause(1000);
        infographicUrl = Driver.getDriver().getCurrentUrl();
        webElementActions.pause(1000);
        webElementActions.navigateBack();
        return this;
    }

    /**
     * Ожидает, пока кнопка курса станет кликабельной, затем кликает по ней с использованием JavaScript
     *
     * @return возвращает новую страницу курсов пользователя
     */
    public UserCoursesPage clickCourses() {
        webElementActions.waitButtonBeClickable(courses)
                .jsClick(courses);
        return new UserCoursesPage();
    }

    /**
     * Ожидает, пока кнопка групп станет кликабельной, затем кликает по ней с использованием JavaScript
     *
     * @return возвращает новую страницу групп пользователя
     */
    public UserGroupsPage clickGroups() {
        webElementActions.waitButtonBeClickable(groups)
                .jsClick(groups);
        return new UserGroupsPage();
    }

//    public UserBranchesPage clickBranches() {
//        webElementActions.waitButtonBeClickable(branches)
//                .jsClick(branches);
//        return new UserBranchesPage();
//    }

    /**
     * Ожидает, пока кнопка файлов станет кликабельной, затем кликает по ней с использованием JavaScript
     *
     * @return возвращает новую страницу файлов пользователя
     */
    public UserFilesPage clickFiles() {
        webElementActions.waitButtonBeClickable(files)
                .jsClick(files);
        return new UserFilesPage();
    }

}