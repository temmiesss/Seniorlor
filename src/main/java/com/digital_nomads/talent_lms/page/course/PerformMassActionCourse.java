package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.entity.CourseActionResult;
import com.digital_nomads.talent_lms.entity.CoursePage;
import com.digital_nomads.talent_lms.enums.CourseAction;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;
import com.github.javafaker.Faker;
import io.cucumber.java.zh_cn.假如;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import static com.digital_nomads.talent_lms.enums.CourseAction.*;

public class PerformMassActionCourse extends BasePage {

        CoursePage coursePage;

        @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
        public WebElement massActionsBtn;

        @FindBy(css = ".submit-icon")
        private WebElement submitIcon;

        @FindBy(css = ".cancel-icon")
        private WebElement cancelIcon;

        @FindBy(xpath = "//*[@id=\"checkbox-items\"]/div/ul/li[1]/a")
        private WebElement activateBtn;

        @FindBy(xpath = "//*[@id=\"submit-mass-action\"]")
        public WebElement activateIcon;

        @FindBy(xpath = "//*[@id=\"tl-courses-mass-action-modal\"]/div[3]/a[2]")
        public WebElement cancelActivate;

        @FindBy(css = "#select-category")
        private WebElement selectCategoryField;

        @FindBy(xpath = "//*[@id=\"checkbox-items\"]/div/ul/li[2]/a")
        public WebElement deactivateBtn;

        @FindBy(xpath = "//a[@class='btn deactivatecourses btn-danger']")
        public WebElement deactivateIconBtn;

        @FindBy(xpath = "//a[@class='btn']")
        public WebElement deactivateCancel;

        @FindBy(xpath = "//a[@data-mode='delete']")
        public WebElement deleteIcon;

        @FindBy(xpath = "//a[@class='btn deletecourses btn-danger']")
        public WebElement deleteBtn;

        @FindBy(xpath = "//a[@class='btn']")
        public WebElement deleteCancel;

        @FindBy(xpath = "//a[@data-mode='branch-add']")
        public WebElement addBranch;

        @FindBy(id = "submit-mass-action")
        public WebElement addBranchBtn;

        @FindBy(xpath = "//a[@class='btn']")
        public WebElement addBranchCancel;


        @FindBy(xpath = "//*[@id=\"checkbox-items\"]/div/a")
        public WebElement showCourseMassActionDropdownMenu;

        @FindBy(xpath = "//a[@data-mode='branch-remove']")
        public WebElement removeFromBranch;

        @FindBy(xpath = "//a[@data-mode='group-add']")
        public WebElement addToGroup;

        @FindBy(xpath = "//a[@data-mode='set-category']") // CSS-селектор списка категорий
        public WebElement setCategoryBtn;

        @FindBy(css = ".dropdown-branch-list li")
        public List<WebElement> branchDropdownItems;

        @FindBy(xpath = "//span[@class='select2-arrow']")
        public List<WebElement> categoryDropdownItems;
        /**
         * @author Rano
         * @method to gropping list of categories
         * @return
         */
        @Step("метод по выпадающему списку категорий")
        public List<String> getDropdownListOfCategories() {
                if (categoryDropdownItems.isEmpty()) {
                        throw new RuntimeException("Список категорий пуст или не найден!");
                }
                return categoryDropdownItems.stream()
                        .map(WebElement::getText) // Получаем текст каждой категории
                        .collect(Collectors.toList()); // Собираем в список
        }
        @Step("метод по выбору категорий")
        public String selectCategoryFromDropdownList() {
                if (categoryDropdownItems.isEmpty()) {
                        throw new RuntimeException("Нет доступных категорий для выбора!");
                }
                WebElement firstCategory = categoryDropdownItems.get(0);
                String categoryName = firstCategory.getText();
                firstCategory.click();
                return categoryName;
        }
        @Step("метод по выпадающему списку веток")
        public List<String> getDropdownListOfBranches() {
                setCategoryBtn.click();
                if (branchDropdownItems.isEmpty()) {
                        throw new RuntimeException("Список категорий пуст или не найден!");
                }
                return branchDropdownItems.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        }

        @Step("метод по выбору  категорий ветвей")
        public String selectBranchesFromDropdownList() {
                if (branchDropdownItems.isEmpty()) {
                        throw new RuntimeException("Нет доступных ветвей для выбора!");
                }
                WebElement firstCategory = branchDropdownItems.get(0);
                String categoryName = firstCategory.getText();
                firstCategory.click();

                return categoryName;
        }
        @Step("метод по выбору  групп")
        public List<String> getDropdownListOfGroup() {
                if (categoryDropdownItems.isEmpty()) {
                        throw new RuntimeException("Список групп пуст или не найден!");
                }
                return categoryDropdownItems.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        }
        @Step("метод по реализации массАктива курсов")
        public CourseActionResult performMassActionForCourse(String courseName, CourseAction action) {
                Actions actions = new Actions(Driver.getDriver());
                WebElement courseElement = driver.findElement(By.xpath("//span[text()='" + courseName + "']"));
                webElementActions.moveToElement(courseElement);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop.fade.in")));

                WebElement userCheckboxElement = driver.findElement(By.xpath("//span[text()='" + courseName + "']/ancestor::tr//input[@type='checkbox']"));

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", userCheckboxElement);

                massActionsBtn.click();

                String selectedCategory = null;
                String selectedBranches = null;

                switch (action) {
                        case ADD_TO_BRANCH -> {
                                webElementActions.click(addBranch);
                                if (!getDropdownListOfBranches().isEmpty()) {
                                        selectedBranches = selectBranchesFromDropdownList();
                                } else cancelIcon.click();
                                submitIcon.click();
                        }
                        case ADD_TO_GROUP -> {
                                webElementActions.click(addToGroup);
                                if (!getDropdownListOfBranches().isEmpty()) {
                                        selectedBranches = selectBranchesFromDropdownList();
                                } else cancelIcon.click();
                                submitIcon.click();
                        }
                        case ACTIVATE -> {
                                webElementActions.click(activateBtn);
                                webElementActions.click(activateIcon);
                                webElementActions.click(cancelActivate);

                        }
                        case DEACTIVATE -> {
                                webElementActions.click(deactivateBtn);
                                webElementActions.click(deactivateIconBtn);
                                webElementActions.click(deactivateCancel);
                        }
                        case DELETE -> {
                                webElementActions.click(deleteBtn);
                                webElementActions.click(deleteIcon);
                                webElementActions.click(deleteCancel);
                        }
                        case SET_TO_CATEGORY -> {
                                webElementActions.click(selectCategoryField);
                                if (!getDropdownListOfCategories().isEmpty()) {
                                        selectedCategory = selectCategoryFromDropdownList();
                                } else cancelIcon.click();
                                submitIcon.click();
                        }
                        default -> throw new IllegalArgumentException("Unknown action: " + action);
                }
                return new CourseActionResult(selectedCategory, selectedBranches);
        }
}
