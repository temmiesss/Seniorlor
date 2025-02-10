package com.digital_nomads.talent_lms.page.category;

import com.digital_nomads.talent_lms.entity.Category;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CategoryPage extends BasePage {
    Category category= new Category();
    AddCategoryPage addCategoryPage = new AddCategoryPage();

    @FindBy(xpath = "//a[normalize-space()='Add category']")
    public WebElement addCategoryBtn;


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
}
