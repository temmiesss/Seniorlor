package com.digital_nomads.talent_lms.page.eventsEngine;
import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.NotificationEntity;
import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomTextGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class EventsEnginePage extends BasePage{

    @FindBy(css = "a[class='btn active']")
    public WebElement notifications;

    @FindBy(css = "th[aria-label='Name']")
    public WebElement sortByName;

    @FindBy(css = "th[aria-label='Event']")
    public WebElement sortByEvent;

    @FindBy(css = "th[aria-label='Recipient']")
    public WebElement sortByRecipient;

    @FindBy(css = "i[alt='Delete']")
    public WebElement deleteIcon;

    @FindBy(css = "#tl-confirm-submit")
    public WebElement deleteConfirm;

    @FindBy(css = ".tl-grid-size-more")
    public WebElement showMoreButton;

    @FindBy(css = "input[name='submit_item']")
    public WebElement updateButton;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement notificationUpdateMessage;

    @FindBy(css = "#s2id_tl-scheduler-event-type span[class ='select2-chosen']")
    public WebElement selectEvent;

    @FindBy(css = "#s2id_tl-scheduler-event-receiver span[class ='select2-chosen']")
    public WebElement selectRecipient;

    AddNotificationPage addNotificationPage = new AddNotificationPage();
    RandomTextGenerator randomTextGenerator = new RandomTextGenerator();


    // Метод для получения строки по имени уведомления
    public WebElement getNotificationRowByName(String notificationName) {
        // Загружаем все уведомления
        List<NotificationEntity> allNotifications = getNotificationsFromTable();

        // Перебираем все уведомления и ищем нужное по имени
        for (NotificationEntity notificationEntity : allNotifications) {
            if (notificationEntity.getNotificationName().equalsIgnoreCase(notificationName)) {
                List<WebElement> rows = Driver.getDriver().findElements(By.cssSelector("tbody tr[role='row']"));
                return rows.get(allNotifications.indexOf(notificationEntity));  // Возвращаем строку, соответствующую уведомлению
            }
        }

        throw new NoSuchElementException("Notification with name '" + notificationName + "' not found.");
    }

    // Метод для удаления уведомления по имени
    public void deleteNotificationByName(String notificationName) {
        WebElement row = getNotificationRowByName(notificationName);
        webElementActions.moveToElement(row);
        WebElement deleteButton = row.findElement(By.cssSelector("i[alt='Delete']"));
        webElementActions.click(deleteButton).click(deleteConfirm);
    }

    public void editNotificationName(String notificationName, String newName) {
        webElementActions
                .click(editNotification(notificationName))
                .clear(addNotificationPage.nameNotificationInput)
                .sendKeys(addNotificationPage.nameNotificationInput, newName)
                .click(updateButton);

    }

    public void editEvent(String notificationName) {
        webElementActions
                .click(editNotification(notificationName))
                .click(selectEvent)
                .click(addNotificationPage.getRandomElement(addNotificationPage.eventNames))
                .click(updateButton);

    }

    public void editRecipient(String notificationName) {
        webElementActions
                .click(editNotification(notificationName))
                .click(selectRecipient)
                .click(addNotificationPage.getRandomElement(addNotificationPage.availableRecipients))
                .click(updateButton);

    }

    public void editNotificationBody(String notificationName) {
        webElementActions
                .click(editNotification(notificationName))
                .clear(addNotificationPage.notificationBody);
        addNotificationPage.fillNotificationBody(randomTextGenerator.randomText1());
        webElementActions.click(updateButton);

    }



    // Метод для получения всех уведомлений
    public ArrayList<NotificationEntity> getNotificationsFromTable() {
        ArrayList<NotificationEntity> notificationEntityList = new ArrayList<>();

        loadMoreRowsIfNeeded(); // Загружаем все строки, если кнопка "Show More" видна

        // Получаем строки таблицы
        List<WebElement> rows = Driver.getDriver().findElements(By.cssSelector("tbody tr[role='row']"));

        notificationEntityList.addAll(processRows(rows)); // Обрабатываем и добавляем строки в список

        return notificationEntityList;
    }


    // Метод для загрузки новых строк (пока кнопка "Show More" доступна)
    private void loadMoreRowsIfNeeded() {
        while (showMoreButton.isDisplayed()) {
            webElementActions.moveToElement(showMoreButton).click(showMoreButton);
            // Ждем, пока новые строки загрузятся
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.stalenessOf(Driver.getDriver().findElements(By.cssSelector("tbody tr[role='row']")).get(0)));
        }
    }

    // Метод для обработки строк таблицы
    private ArrayList<NotificationEntity> processRows(List<WebElement> rows) {
        ArrayList<NotificationEntity> notificationEntityList = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".tl-align-left"));

            String notificationName = cells.get(0).getText();
            String status = "ACTIVE";
            if (notificationName.contains("INACTIVE")) {
                notificationName = notificationName.replace("INACTIVE", "").trim();
                status = "INACTIVE";
            }
            String eventName = cells.get(1).getText();
            String recipient = cells.get(2).getText();

            // Если хотя бы одно поле пустое, пропускаем строку
            if (notificationName.isEmpty() || eventName.isEmpty() || recipient.isEmpty()) {
                continue;
            }

            notificationEntityList.add(new NotificationEntity(notificationName, status, eventName, recipient));
        }

        return notificationEntityList;
    }

    private WebElement editNotification(String name){
        WebElement row = getNotificationRowByName(name);
        webElementActions.moveToElement(row);
        WebElement editButton = row.findElement(By.cssSelector("i[alt='Edit']"));
        return editButton;
    }

    public NotificationEntity getRandomNotification(){
        return getNotificationsFromTable().get(random.nextInt(getNotificationsFromTable().size()));

    }

}