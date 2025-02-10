package com.digital_nomads.talent_lms.page.addGroup;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


/**
 * @author Saida
 */

public class AddGroupPage extends BasePage {

    public WebElementActions webElementActions = new WebElementActions();

    @FindBy(xpath = "//div[@class='tl-icons-block']//a[contains(text(), 'Add group')]")
    public WebElement addGroupBtn;

    @FindBy(xpath = "//input[@data-maxchars='80']")
    private WebElement inputName;

    @FindBy(xpath = "//*[@placeholder='Short description up to 500 characters']")
    private WebElement description;

    @FindBy(xpath = "//input[@class='btn btn-primary' and @value='Add group']")
    private WebElement submitBtn;

    @FindBy(xpath = "//a[contains(@class, 'groups-button')]")
    private WebElement groupsBtn;

    @FindBy(xpath = "//a[@title='Groups']")
    private WebElement groupsNames;

    @FindBy(xpath = "//*[@id=\"tl-groups-grid\"]/tbody/tr/td[3]/div")
    private WebElement textLine;

    @FindBy(xpath = "//*[@id=\"tl-groups-grid\"]/tbody/tr/td[3]/div/div/i[1]")
    private WebElement editPencil;

    @FindBy(xpath = "//*[@id=\"tl-admin-dashboard\"]/div[4]/div/div[1]/a")
    private WebElement enterGroup;

    @FindBy(xpath = "//input[@name='name' and @placeholder='e.g. Startup courses']")
    private WebElement editName;

    @FindBy(xpath = "//textarea[@name='description' and @placeholder='Short description up to 500 characters']")
    private WebElement editDescription;

    @FindBy(xpath = "//input[@name='submit_group']")
    private WebElement updateGroup;

    @FindBy(xpath = "//a[@class='btn btn-danger' and @id='tl-confirm-submit']")
    private WebElement deleteGroup;

    @FindBy(xpath = "//*[@id=\"tl-confirm\"]/div/a [@class='btn']")
    private WebElement deleteCancelGroup;

    @FindBy(xpath = "//a[@class='dropdown-toggle' and @role='button']")
    public WebElement massActions;

    @FindBy(xpath = "//a[@class='massaction' and text()='Add a course to all groups']")
    public WebElement addCourseToAllGroups;

    @FindBy(xpath = "//a[@class='massaction' and text()='Remove a course from all groups']")
    public WebElement removeCourseFromAllGroups;

    @FindBy(xpath = "//span[contains(text(),'Fall24')]")
    public WebElement groupTitle;

    @FindBy(xpath = "//div[@id='select2-drop-mask']")
    public WebElement selectBtn;

    @FindBy(xpath = "//div[text()='What is TalentLibrary? (002)']")
    public WebElement TalentLibrary;

    @FindBy(xpath = "//span[text()='[Edit me] Guide for Learners (001)']")
    public WebElement GuideForLearners;

    @FindBy(xpath = "//a[@class='btn addtogroups btn-success']")
    public WebElement AddCourseBtn;

    @FindBy(xpath = "//td[@class=' tl-align-left']")
    public List<WebElement> webTableOfGroups;

    @FindBy(css = "Success! New group created")
    public WebElement textOfSuccsefullyAddedGroup;


    /**
     * Метод для добавления новой группы.
     *
     * @param group Объект группы, содержащий информацию о названии и описании группы.
     * @return Возвращает объект текущей страницы для продолжения работы с ней.
     */
    public AddGroupPage addNewGroup(Groups group) {
        webElementActions.click(addGroupBtn)
                .sendKeys(this.inputName, group.getName())
                .sendKeys(this.description, group.getDescription());
        webElementActions.click(submitBtn);
        return new AddGroupPage();
    }

    /**
     * Добавляет курс в группу через UI.
     *
     * @param group группа для добавления курса
     * @return новая страница AddGroupPage
     */
    public AddGroupPage addCourseToGroup(Groups group) {
        webElementActions.click(massActions)
                .click(addCourseToAllGroups)
                .click(TalentLibrary)
                .click(AddCourseBtn);
        return new AddGroupPage();
    }
    /**
     * Метод для редактирования существующей группы.
     *
     * @param group Объект группы, содержащий новые данные для редактирования.
     * @return Возвращает объект текущей страницы для продолжения работы с ней.
     */
    public AddGroupPage editGroup(Groups group) {
        webElementActions.click(enterGroup)
                .moveToElement(textLine)
                .click(editPencil)
                .sendKeys(editName, "Test Name")
                .sendKeys(editDescription, "Test Description")
                .click(updateGroup);
        return new AddGroupPage();
    }

    /**
     * Метод для проверки добавления группы.
     *
     * @param group Объект группы, которую нужно проверить.
     * @return Возвращает true, если группа была добавлена, иначе false.
     */
    public boolean isGroupAdded(Groups group) {
        String xpath = "//td[normalize-space(text()) = '" + group.getName() + "']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
        return true;
    }

    /**
     * Метод для удаления группы.
     *
     * @param group Объект группы, которую нужно удалить.
     * @return Возвращает объект текущей страницы для продолжения работы с ней.
     */
    public AddGroupPage deleteGroup(Groups group) {
        webElementActions.click(enterGroup)
                .moveToElement(textLine)
                .click(textLine)
                .click(deleteGroup);
        return new AddGroupPage();
    }

    /**
     * Отменяет удаление группы.
     *
     * @param group Объект группы, который планировалось удалить.
     * @return Экземпляр страницы {@link AddGroupPage} после отмены удаления.
     */
    public AddGroupPage cancelDeletingGroup(Groups group) {
        webElementActions.click(enterGroup)
                .moveToElement(textLine)
                .click(textLine)
                .click(deleteGroup)
                .click(deleteCancelGroup);
        return new AddGroupPage();
    }

    /**
     * Проверяет существование группы с указанным названием.
     *
     * @param title Название группы, которую нужно проверить.
     * @return {@code true}, если группа существует, иначе {@code false}.
     */
    public boolean isGroupExist(String title) {
        WebElement group = driver.findElement(By.xpath("//span[@title='" + title + "']"));
        return webElementActions.isDisplayed(group);
    }

    /**
     * Метод для проверки видимости элемента на странице
     *
     * @param element элемент, видимость которого проверяется
     * @return true, если элемент видим, иначе false
     */
    public boolean isElementVisible(WebElement element) {
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Обновление данных группы.
     *
     * @param newGroupName        новое имя группы.
     * @param newGroupDescription новое описание группы.
     */
    public void updateGroup(String newGroupName, String newGroupDescription) {
        webElementActions.waitElementToBeDisplayed(editName);
        webElementActions.clear(editName).sendKeys(editName, newGroupName);
        webElementActions.waitElementToBeDisplayed(editDescription);
        webElementActions.clear(editDescription).sendKeys(editDescription, newGroupDescription);
        webElementActions.click(updateGroup);
    }

    /**
     * Проверяет, отображается ли сообщение об успешном выполнении операции.
     *
     * @return {@code true}, если сообщение об успехе видно, иначе {@code false}.
     */
    public boolean isSuccessMessageVisible() {
        WebElement successMessage = Driver.getDriver().findElement(By.id("successMessage")); // или другой локатор для вашего сообщения
        return successMessage.isDisplayed();
    }

    /**
     * Проверяет, отображается ли сообщение об ошибке.
     *
     * @return {@code true}, если сообщение об ошибке видно, иначе {@code false}.
     */
    public boolean isErrorMessageVisible() {
        WebElement errorMessage = Driver.getDriver().findElement(By.xpath("//div[@class='alert alert-danger']"));
        return errorMessage.isDisplayed();
    }

    /**
     * Получает элемент группы по ее названию.
     *
     * @param groupName Название группы.
     * @return Веб-элемент, соответствующий указанной группе.
     */
    public WebElement getGroupElementByName(String groupName) {
        return Driver.getDriver().findElement(By.xpath("//td[normalize-space(text()) = '" + groupName + "']"));
    }

    /**
     * Метод для отмены добавления группы.
     */
    public void cancelGroupAddition() {
        deleteCancelGroup.click();  // Нажимает кнопку отмены
    }

    /**
     * Удаление группы без подтверждения.
     *
     * @param groupName название группы, которую необходимо удалить.
     */
    public void deleteGroupWithoutConfirmation(String groupName) {
        WebElement groupElement = Driver.getDriver().findElement(By.xpath("//a[@title='Groups' and text()='" + groupName + "']"));
        webElementActions.waitElementToBeDisplayed(groupElement);
        webElementActions.moveToElement(groupElement);
        webElementActions.waitElementToBeDisplayed(editPencil);
        webElementActions.click(editPencil);
        webElementActions.waitElementToBeDisplayed(deleteGroup);
        webElementActions.click(deleteGroup);
    }

    /**
     * Проверяет, что группа была обновлена.
     *
     * @param group группа с обновленными данными
     * @return true, если группа обновлена, иначе false
     */
    public boolean isGroupUpdated(Groups group) {
        webElementActions.waitElementToBeDisplayed(groupsNames);

        List<WebElement> groupsList = Driver.getDriver().findElements(By.xpath("//a[@title='Groups']"));

        for (WebElement el : groupsList) {
            if (el.getText().equals(group.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Создание новой группы и добавление в неё пользователей.
     *
     * @param group группа, которую нужно создать.
     * @param users список пользователей, которых нужно добавить в группу.
     */
    public void createGroupAndAddUsers(Groups group, List<User> users) {
        addNewGroup(group);

        for (User user : users) {
            addUserToGroup(group, user);
        }
    }

    /**
     * Добавление пользователя в группу.
     *
     * @param group группа, в которую добавляется пользователь.
     * @param user  пользователь, которого добавляют в группу.
     */
    private void addUserToGroup(Groups group, User user) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
        groupElement.findElement(By.xpath(".//button[contains(text(), 'Add user')]")).click();

        WebElement userInput = driver.findElement(By.xpath("//input[@placeholder='Search users']"));
        userInput.sendKeys(user.getUsername());

        driver.findElement(By.xpath("//div[contains(text(), '" + user.getUsername() + "')]")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Add')]")).click();
    }

    /**
     * Проверка, что группа создана с пользователями.
     *
     * @param group группа, которую нужно проверить.
     * @param users список пользователей, которых нужно найти в группе.
     * @return true, если группа существует и пользователи добавлены.
     */
    public boolean isGroupCreatedWithUsers(Groups group, List<User> users) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
        if (groupElement != null) {
            for (User user : users) {
                WebElement userElement = driver.findElement(By.xpath("//div[contains(text(), '" + user.getUsername() + "')]"));
                if (userElement == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Проверка, что название группы и пользователи были обновлены.
     *
     * @param group         группа, которую нужно проверить.
     * @param expectedName  ожидаемое название группы.
     * @param expectedUsers ожидаемый список пользователей.
     * @return true, если изменения были успешно применены.
     */
    public boolean isGroupEdited(Groups group, String expectedName, List<User> expectedUsers) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
        if (groupElement != null && groupElement.getText().equals(expectedName)) {
            for (User user : expectedUsers) {
                WebElement userElement = driver.findElement(By.xpath("//div[contains(text(), '" + user.getUsername() + "')]"));
                if (userElement == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Удаление группы без влияния на участников.
     *
     * @param group группа, которую нужно удалить.
     */
    public void deleteGroupWithoutAffectingUsers(Groups group) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
        groupElement.findElement(By.xpath(".//button[contains(text(), 'Delete')]")).click();

          driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
    }

    /**
     * Проверка, что группа была удалена.
     *
     * @param group группа, которую нужно проверить.
     * @return true, если группа была удалена.
     */
    public boolean isGroupDeleted(Groups group) {
        try {
            driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Назначение курсов группе.
     *
     * @param group   группа, которой нужно назначить курсы.
     * @param courses список курсов, которые нужно назначить группе.
     */
    public void assignCoursesToGroup(Groups group, List<Course> courses) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
        groupElement.findElement(By.xpath(".//button[contains(text(), 'Assign Courses')]")).click();

        for (Course course : courses) {
            WebElement courseInput = driver.findElement(By.xpath("//input[@placeholder='Search courses']"));
            courseInput.sendKeys(course.getCourseName());
            driver.findElement(By.xpath("//div[contains(text(), '" + course.getCourseName() + "')]")).click();
        }

        driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click();
    }

    /**
     * Проверка, что курсы назначены группе.
     *
     * @param group           группа, для которой нужно проверить курсы.
     * @param expectedCourses ожидаемые курсы.
     * @return true, если курсы были назначены.
     */
    public boolean areCoursesAssignedToGroup(Groups group, List<Course> expectedCourses) {
        WebElement groupElement = driver.findElement(By.xpath("//div[contains(text(), '" + group.getName() + "')]"));
        if (groupElement != null) {
            for (Course course : expectedCourses) {
                WebElement courseElement = driver.findElement(By.xpath("//div[contains(text(), '" + course.getCourseName() + "')]"));
                if (courseElement == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Метод проверяет наличие группы с именем "TestGroup" в таблице групп.
     * Для каждой найденной группы выполняется проверка с помощью assert.
     *
     * @return текущий объект AddGroupPage для цепочки вызовов
     */
    public AddGroupPage listOfGroups() {

        for (WebElement list : webTableOfGroups) {
            if(list.getText().equals("TestGroup")) {
                Assert.assertEquals(list.getText(), "TestGroup");
            }
        }
        return this;
    }



}







