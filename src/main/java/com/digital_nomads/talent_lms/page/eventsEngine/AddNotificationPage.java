package com.digital_nomads.talent_lms.page.eventsEngine;

import com.digital_nomads.talent_lms.page.BasePage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomTextGenerator;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class AddNotificationPage extends BasePage {
    RandomTextGenerator randomTextGenerator = new RandomTextGenerator();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();

    @FindBy(xpath = "//a[normalize-space(text()) = 'Add notification']")
    public WebElement addNotification;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameNotificationInput;

    @FindBy(xpath = "//span[normalize-space(text()) = 'Select event']")
    public WebElement selectEventDropdown;

    @FindBy(xpath = "//input[@class='select2-input select2-focused']")
    public WebElement eventNameInput;

    @FindBy(xpath = "//div[@class='select2-result-label' and not(text()='Select event')]")
    public List<WebElement> eventNames;

    @FindBy(xpath = "//span[normalize-space(text()) = 'Select recipient']")
    public WebElement selectRecipientDropdown;

    @FindBy(xpath = "//li[@class='select2-results-dept-0 select2-result select2-result-selectable']")
    public List<WebElement> availableRecipients;

    @FindBy(xpath = "//textarea[@name='notification_content']")
    public WebElement notificationBody;

    @FindBy(xpath = "//input[@name='status']")
    public WebElement status;

    @FindBy(xpath = "//input[@name='submit_btn']")
    public WebElement submitButton;

    @FindBy(xpath = "//textarea[@name='specific_recipients']")
    public WebElement specificRecipientsEmail;

    @FindBy(xpath = "//input[@name='level' or @name='hours']")
    public WebElement defineLevelHours;

    @FindBy(xpath = "//a[normalize-space(text()) = 'cancel']")
    public WebElement cancelCreatingNotification;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement succeedMessage;

    @FindBy(xpath = "//span[@class='help-inline' and contains(text(),'Name')]")
    public WebElement nameRequiredMessage;

    @FindBy(xpath = "//span[@class='help-inline' and contains(text(),'Event')]")
    public WebElement eventRequiredMessage;

    @FindBy(xpath = "//span[@class='help-inline' and contains(text(),'Recipient')]")
    public WebElement recipientRequiredMessage;

    @FindBy(xpath = "//span[contains(text(),'Body')]")
    public WebElement bodyRequiredMessage;

    @FindBy(xpath = "//span[@class='help-block' and contains(text(),'Specific recipient')]")
    public WebElement specificRecipientRequiredMessage;

    @FindBy(xpath = "//span[@class='help-block' and contains(text(),'Define hours')]")
    public WebElement defineHoursRequiredMessage;


    //  Открыть форму создания уведомления
    public AddNotificationPage openNotificationForm() {
        webElementActions.click(addNotification);
        return this;
    }

    //  Ввести имя уведомления
    public AddNotificationPage enterNotificationName(String name) {
        webElementActions.sendKeys(nameNotificationInput, name);
        return this;
    }

    public AddNotificationPage selectRandomEvent(boolean fillHours) {
        webElementActions.click(selectEventDropdown);
        WebElement randomEvent = getRandomElement(eventNames);
        String eventText = randomEvent.getText();
        webElementActions.click(randomEvent);

        handleSpecificFields(eventText, fillHours); // Обработка поля hours
        return this;
    }

    public AddNotificationPage selectRandomRecipient(boolean fillEmail) {
        webElementActions.click(selectRecipientDropdown);
        WebElement randomRecipient = getRandomElement(availableRecipients);
        String recipientText = randomRecipient.getText();
        webElementActions.click(randomRecipient);

        handleSpecificFields(recipientText, fillEmail); // Обработка email
        return this;
    }

    // Обрабатывает доп. поля для события, но с параметром fillHours
    private void handleSpecificFields(String text, boolean fill) {
        if ((text.contains("hours") || text.contains("level")) && fill) {
            webElementActions.sendKeys(defineLevelHours, String.valueOf(ThreadLocalRandom.current().nextInt(1, 10)));
        }
        if ("Specific recipients".equalsIgnoreCase(text) && fill) {
            webElementActions.sendKeys(specificRecipientsEmail, randomUserGenerator.randomEmail());
        }
    }

    //  Ввести текст уведомления
    public AddNotificationPage fillNotificationBody(String text) {
        webElementActions.sendKeys(notificationBody, text);
        return this;
    }

    //  Установить статус "неактивный"
    public AddNotificationPage setInactive() {
        webElementActions.click(status);
        return this;
    }

    //  Нажать кнопку "Создать уведомление"
    public EventsEnginePage createNotification() {
        webElementActions.click(submitButton);
        return new EventsEnginePage();
    }

    //  Отменить создание уведомления
    public EventsEnginePage cancelCreatingNewNotification() {
        webElementActions.click(cancelCreatingNotification);
        return new EventsEnginePage();
    }

    public EventsEnginePage addNewNotification() {
        return openNotificationForm()
                .enterNotificationName(randomTextGenerator.randomText1())
                .selectRandomEvent(true)
                .selectRandomRecipient(true)
                .fillNotificationBody(randomTextGenerator.randomText1())
                .createNotification();
    }

    public String checkColour(WebElement element){
        String color = element.getCssValue("color");
        return Color.fromString(color).asHex();

    }


    public WebElement getRandomElement(List<WebElement> list) {

        if (list.isEmpty()) {
            throw new IllegalArgumentException("The list is empty");
        }

        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }





}

