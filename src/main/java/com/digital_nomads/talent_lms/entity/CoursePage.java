package com.digital_nomads.talent_lms.entity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CoursePage {

    public WebDriver driver;

    @FindBy(css = ".mass-actions-btn")
    private WebElement massActionsBtn;

    // Конструктор класса
    public CoursePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика по кнопке и вызова действия
    public void openMassActionMenu(String action) {
        if (massActionsBtn != null) {
            massActionsBtn.click(); // Кликаем по кнопке
            showCourseMassActionDropdownMenu(action); // Вызываем нужное действие
        } else {
            throw new RuntimeException("Кнопка massActionsBtn не найдена!");
        }
    }

    // Заглушка метода (реализацию дописать)
    public void showCourseMassActionDropdownMenu(String action) {
        System.out.println("Выполняется действие: " + action);
        // Здесь добавить код для выбора действия в выпадающем меню
    }

    @FindBy(css = ".dropdown-category-list li") // CSS-селектор списка категорий
    public List<WebElement> categoryDropdownItems;


    // Метод для получения списка категорий
    public List<String> getDropdownListOfCategories() {
        if (categoryDropdownItems.isEmpty()) {
            throw new RuntimeException("Список категорий пуст или не найден!");
        }
        return categoryDropdownItems.stream()
                .map(WebElement::getText) // Получаем текст каждого элемента
                .collect(Collectors.toList()); // Собираем в список
    }
}


