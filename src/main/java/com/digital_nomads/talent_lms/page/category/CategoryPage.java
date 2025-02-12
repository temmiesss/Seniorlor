package com.digital_nomads.talent_lms.page.category;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Category;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class CategoryPage extends BasePage {
    Category category= new Category();
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    @FindBy(xpath = "//a[normalize-space()='Add category']")
    public WebElement addCategoryBtn;

    @FindBy(xpath = "//*[@id='tl-categories-grid']/tbody/tr[1]/td[2]/div")
    public WebElement optionBtn;

    @FindBy(xpath = "//*[@id='tl-categories-grid']/tbody//i[@title='Edit']")
    public WebElement editBtn;

    @FindBy(xpath = "//*[@id='tl-categories-grid']/tbody/tr/td/div/div/i[@title='Delete']")
    public WebElement deleteBtn;

    @FindBy(xpath = "//*[@id='tl-confirm-submit']")
    public WebElement alertDeleteBtn;

    @FindBy(xpath = "//*[@id='tl-confirm']/div/a[@class='btn']")
    public WebElement alertCancleBtn;

    @FindBy(xpath = "//*[@id='tl-confirm']/div/a[@class='close']")
    public WebElement alertCloseBtn;


    public AddCategoryPage AddNewCategory(){
        webElementActions.click(addCategoryBtn);
        webElementActions.sendKeys(addCategoryPage.categoryName, enterCategoryName())
//                .randomClick(addCategoryPage.selectorOfParentCategory)
//                .randomClick(addCategoryPage.priceSetter)
                .click(addCategoryPage.addCategoryBtn);
        return new AddCategoryPage();
    }

    public void CancelCategoryBtn(){
        webElementActions.click(addCategoryPage.cancelCategoryBtn);
    }

    public String enterCategoryName() {
        return category.getCatName();
    }

    public void OptionButtonEditor(){
        actions.moveToElement(optionBtn).click(editBtn).perform();
    }

    public void DeleteAlertInOptionBtn() throws InterruptedException {
        actions.moveToElement(optionBtn)
                .click(deleteBtn)
                .perform();
        Thread.sleep(5000);
    }

    public void ChangeCategoryName(){
        addCategoryPage.categoryName.clear();
        webElementActions.sendKeys(addCategoryPage.categoryName,"cvbnmjuhyuiolas");
        addCategoryPage.addCategoryBtn.click();
    }

    public void ChangeCategoryPrice(){
        webElementActions.click(addCategoryPage.priceSetter);
        addCategoryPage.priceSetter2ndStage.clear();
        webElementActions.sendKeys(addCategoryPage.priceSetter2ndStage,"999");
        addCategoryPage.addCategoryBtn.click();
    }



//    public ArrayList<Category> getCategoryNameFromTable() {
//        // Получаем список всех ячеек таблицы (rows) с помощью селектора CSS
//        // В этом листе "rows" хранится одна строка со всеми данными сотрудника
//        List<WebElement> rows = Driver.getDriver().findElements(By.xpath("//*[@id='tl-categories-grid']/tbody/tr")); //4
//
//        // Создаем пустой список для хранения объектов EmployeeEntity
//        ArrayList<Category> categoriesNames = new ArrayList<>();
//
//        // Перебираем каждую ячейку таблицы
//        for (WebElement row : rows) {
//            // Получаем все ячейки (cells) текущей строки с помощью селектора CSS
//            List<WebElement> cells = row.findElements(By.xpath("//*[@id='tl-categories-grid']/tbody/tr"));
//
//            // Извлекаем текст из каждой ячейки и сохраняем в переменные
//            String Name = cells.get(0).getText();
//
//            // Создаем новый объект EmployeeEntity и добавляем его в список employeeEntities
//            categoriesNames.add((new Category()));
//
//        }
//        // Возвращаем список объектов EmployeeEntity
//        return categoriesNames;
//    }
}

