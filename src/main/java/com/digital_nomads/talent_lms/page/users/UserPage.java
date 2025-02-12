package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.ActionResult;
import com.digital_nomads.talent_lms.enums.Action;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Akylai
 */
public class UserPage extends BasePage {
    private Actions actions = new Actions(driver);
    private Faker faker = new Faker();

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUser;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    public WebElement massActionsBtn;

    @FindBy(xpath = "//div[@class='btn-group open']/ul")
    public WebElement actionsDropdownMenu;

    @FindBy(xpath = "//div[@id='tl-mass-groups']//li[@class='select2-search-field']")
    public WebElement selectGroupField;

    @FindBy(id = "tl-mass-branches")
    public WebElement selectBranchField;

    @FindBy(xpath = "//input[@id='message_subject']")
    public WebElement subjectInputField;

    @FindBy(xpath = "//div[@class='note-editable tl-message-editor span9']")
    public WebElement messageInputField;

    @FindBy(xpath = "//input[@id='submit_send_message']")
    public WebElement sendMessageBtn;

    @FindBy(xpath = "//*[@id='tl-users-grid']/tbody/tr[1]/td[3]/span")
    public WebElement firstUserEmailElement;

    @FindBy(xpath = "//*[@id='tl-users-grid']/tbody/tr[1]/td[2]/a/span")
    public WebElement firstUserUsernameElement;

    @FindBy(xpath = "//a[@id='submit-mass-action']")
    public WebElement submitIcon;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteIcon;

    @FindBy(xpath = "//div[@id='tl-confirm']/descendant::a[@class='btn']")
    public WebElement cancelIcon;

    @FindBy(xpath = "//input[@name='submit_send_message']/../a[@class='btn']")
    public WebElement canselSendingMsgBtn;

    @FindBy(xpath = "//div[@class='control-group tl-message-subject error']/div/span/span")
    public WebElement subjectErrorMessage;


    /**
     * Переход на страницу добавления пользователя
     *
     * @return Возвращает страницу AddUserPage для регистрации пользователя
     */
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUser);
        return new AddUserPage();
    }

    /**
     * Следующие 2 метода предназначены для получения текста с элементов веб-страницы
     *
     * @return возвращают текстовое содержимое элемента
     */
    public String getFirstUserEmail() {
        return firstUserEmailElement.getText();
    }

    public String getFirstUserUsername() {
        return firstUserUsernameElement.getText();
    }

    public List<String> getUserList() {
        return driver.findElements(By.cssSelector(".tl-align-left.hidden-phone span[title]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Выполняет удаление пользователя по email.
     *
     * @param userEmail указывает на определенный email, который требуется найти
     * @return Возвращает новую страницу UserPage или текущую, если пользователь не найден
     */
    public UserPage deleteUser(String userEmail) {
        try {
            WebElement emailElement = driver.findElement(By.xpath("//span[text()='" + userEmail + "']"));
            webElementActions.moveToElement(emailElement);
            WebElement deleteOption = driver.findElement(By.xpath("//span[text()='" + userEmail + "']/ancestor::tr//i[@title='Delete']"));
            webElementActions.click(deleteOption);

            WebElement deleteConfirmation = wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
            deleteConfirmation.click();
            return new UserPage();

        } catch (NoSuchElementException e) {
            System.out.println("User not found");
            return this;
        }
    }

    /**
     * Выполняет отмену удаления пользователя.
     *
     * @param userEmail используется для поиска в списке указанного email
     * @return Возвращает текущую страницу UserPage или остается на ней, если пользователь не найден
     */
    public UserPage cancelDeleteUser(String userEmail) {
        try {
            WebElement emailElement = driver.findElement(By.xpath("//span[text()='" + userEmail + "']"));
            webElementActions.moveToElement(emailElement);
            WebElement deleteOption = driver.findElement(By.xpath("//span[text()='" + userEmail + "']/ancestor::tr//i[@title='Delete']"));
            webElementActions.click(deleteOption);

            WebElement cancelConfirmation = wait.until(ExpectedConditions.elementToBeClickable(cancelIcon));
            cancelConfirmation.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + userEmail + "']")));
            return this;
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
            return this;
        }
    }

    /**
     * Проверяет наличие пользователя в списке, используется в методе testDeleteUser(), класс UserPageTest
     *
     * @param userEmail используется для поиска в списке указанного email
     * @return Возвращает true, если найден хотя бы один отображаемый элемент, иначе false.
     * В случае StaleElementReferenceException возвращает false
     */
    public boolean isUserPresent(String userEmail) {
        try {
            List<WebElement> elements = driver.findElements(By.xpath("//span[text()='" + userEmail + "']"));
            System.out.println("Количество найденных элементов: " + elements.size());
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Выполняет переход на страницу редактирования.
     *
     * @param userEmail Ищет элемент с указанным email
     * @return Возвращает новую страницу EditUserDataPage.
     * Если пользователь не найден, выводит сообщение и возвращает null
     */
    public EditUserDataPage editUser(String userEmail) {
        try {
            WebElement emailElement = driver.findElement(By.xpath("//span[text()='" + userEmail + "']"));
            webElementActions.moveToElement(emailElement);
            WebElement editOption = driver.findElement(By.xpath("//span[text()='" + userEmail + "']/ancestor::tr//i[@title='Edit']"));
            webElementActions.click(editOption);
            return new EditUserDataPage();

        } catch (NoSuchElementException e) {
            System.out.println("User not found");
            return null;
        }
    }

    /**
     * Открывает отчеты пользователя.
     *
     * @param userEmail Ищет элемент с указанным email
     */
    public void userReportsOption(String userEmail) {
        try {
            WebElement emailElement = driver.findElement(By.xpath("//span[text()='" + userEmail + "']"));
            webElementActions.moveToElement(emailElement);
            WebElement reportsOption = driver.findElement(By.xpath(
                    "//span[text()='" + userEmail + "']/ancestor::tr//i[@title='Reports']"));
            webElementActions.click(reportsOption);
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
    }

    /**
     * Выполняет вход в аккаунт пользователя
     *
     * @param userEmail Ищет элемент с указанным email
     */
    public void logIntoAccountOption(String userEmail) {
        try {
            WebElement emailElement = driver.findElement(By.xpath(
                    "//span[text()='" + userEmail + "']"));
            webElementActions.moveToElement(emailElement);
            WebElement logIntoAccountOption = driver.findElement(By.xpath(
                    "//span[text()='" + userEmail + "']/ancestor::tr//i[@title='Log into account']"));
            webElementActions.click(logIntoAccountOption);
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
    }

    /**
     * Метод отвечает за отображение выпадающего меню массовых действий и выбор соответствующего
     * действия на основе переданного параметра action
     *
     * @param action подразумевает значение выбранной переменной (DELETE, ACTIVATE и т.п.)
     */
    public void showMassActionDropdownMenu(Action action) {
        WebElement actionElement = actionsDropdownMenu.findElement(By.xpath(".//a[normalize-space()='" + action.getActionName() + "']"));
        actionElement.click();
    }

    /**
     * перегруженную версию метода, где передается третий параметр false, указывающий на то, что поле не нужно очищать по умолчанию
     *
     * @param userEmail - параметр performMassAction, email пользователя
     * @param action    - параметр performMassAction, действие
     * @return - результат работы перегруженной версии performMassAction, который в свою очередь возвращает объект типа ActionResult
     */
    public ActionResult performMassAction(String userEmail, Action action) {
        return performMassAction(userEmail, action, false);  // По умолчанию не очищаем поле
    }

    /**
     * Выполняет массовое действие над пользователем (действия из MassActions)
     *
     * @param userEmail Находит элемент с указанным email
     * @param action    выбор действия из выпадающего списка
     * @return Возвращает результат по каждому действию (action)
     */
    public ActionResult performMassAction(String userEmail, Action action, Boolean clearField) {
        WebElement emailElement = driver.findElement(By.xpath("//span[text()='" + userEmail + "']"));
        actions.moveToElement(emailElement).click().perform();

        // Ожидание исчезновения модального окна, если оно есть
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop.fade.in")));

        WebElement userCheckboxElement = driver.findElement(By.xpath("//span[@title='" + userEmail + "']/ancestor::tr//input[@type='checkbox']"));
        // Клик по чекбоксу через JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", userCheckboxElement);

        massActionsBtn.click();
        showMassActionDropdownMenu(action);

        String selectedGroup = null;
        String selectedBranch = null;

        switch (action) {
            case DELETE, ACTIVATE, DEACTIVATE:
                webElementActions.click(submitIcon);
                break;
            case ADD_TO_BRANCH:
                webElementActions.click(selectBranchField);
                if (!getDropdownListOfBranches().isEmpty()) {
                    selectedBranch = selectBranchFromDropdownList();
                } else cancelIcon.click();
                submitIcon.click();
                break;
            case ADD_TO_GROUP:
                webElementActions.click(selectGroupField);
                if (!getDropdownListOfGroups().isEmpty()) {
                    selectedGroup = selectGroupFromDropdownList();
                } else cancelIcon.click();
                submitIcon.click();
                break;
            case SEND_MESSAGE:
                String randomSubject = faker.lorem().sentence();
                String randomMessage = faker.lorem().paragraph();
                if (clearField) {
                    webElementActions.clear(subjectInputField);
                    webElementActions.sendKeys(messageInputField, randomMessage);
                    webElementActions.click(sendMessageBtn);
                    wait.until(ExpectedConditions.visibilityOf(subjectErrorMessage));
                    webElementActions.click(canselSendingMsgBtn);
                } else {
                    webElementActions.sendKeys(subjectInputField, randomSubject);
                    webElementActions.sendKeys(messageInputField, randomMessage);
                    webElementActions.click(sendMessageBtn);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
        return new ActionResult(selectedGroup, selectedBranch);
    }

    /**
     * Извлекает все элементы из выпадающего списка для выбора
     *
     * @return возвращает String, представляющий текст выбранной ветви из выпадающего списка. Если ветви не были найдены, возвращается null
     */
    public String selectBranchFromDropdownList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> branchElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("select#tl-branches-select option")));

        String selectedBranch = null;
        if (!branchElements.isEmpty()) {
            selectedBranch = branchElements.get(1).getText().trim();
            for (WebElement branchElement : branchElements) {
                if (branchElement.getText().trim().equals(selectedBranch)) {
                    wait.until(ExpectedConditions.elementToBeClickable(branchElement));
                    branchElement.click();
                    break;
                }
            }
        } else {
            System.out.println("No branches available in the dropdown list");
        }
        return selectedBranch;
    }

    /**
     * Метод аналогичен предыдущему, но тут он работает со списком групп
     *
     * @return возвращает String, представляющий текст выбранной группы из выпадающего списка.
     */
    public String selectGroupFromDropdownList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> groupElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("select#tl-groups-select option")));

        String selectedGroup = null;
        if (!groupElements.isEmpty()) {
            selectedGroup = groupElements.get(1).getText().trim();
            for (WebElement groupElement : groupElements) {
                if (groupElement.getText().trim().equals(selectedGroup)) {
                    wait.until(ExpectedConditions.elementToBeClickable(groupElement));
                    groupElement.click();
                    break;
                }
            }
        } else {
            System.out.println("No groups available in the dropdown list");
        }
        return selectedGroup;
    }

    /**
     * Следующие 2 метода - вспомогательные методы для проверки
     *
     * @return возвращают списки элементов
     */
    public List<WebElement> getDropdownListOfGroups() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("select#tl-groups-select option")));
        return elements.isEmpty() ? Collections.emptyList() : elements;
    }

    public List<WebElement> getDropdownListOfBranches() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("select#tl-branches-select option")));
        return elements.isEmpty() ? Collections.emptyList() : elements;
    }

    /**
     * Получает сообщение о выполненном массовом действии
     *
     * @return возвращает текст сообщения всплывающего уведомления
     */
    public String getMassActionMessage() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='toast-message']")));

        // Сохраняем текст через JavaScript (даже если элемент исчезнет)
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String messageText = (String) js.executeScript("return arguments[0].innerText;", messageElement);
        return messageText;
    }

    /**
     * Извлекает текст ошибки
     *
     * @return String, текст сообщения
     */
    public String getInvalidSubjectMessage() {
        return subjectErrorMessage.getText();
    }


}
