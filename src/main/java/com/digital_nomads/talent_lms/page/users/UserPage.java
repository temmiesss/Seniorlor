package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.enums.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.digital_nomads.talent_lms.page.BasePage;

/**
 * @author Akylai
 */
public class UserPage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-primary' and text()='Add user']")
    public WebElement addUser;

    @FindBy(xpath = "//td[@class=' tl-align-center tl-grid-checkbox-wrapper hidden-phone']/input")
    public WebElement checkboxTick;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    public WebElement massActionsBtn;

    @FindBy(xpath = "//div[@class='btn-group open']/ul/li")
    public WebElement actionsDropdownMenu;

    @FindBy(xpath = "//a[@id='submit-mass-action']")
    public WebElement submitButton;

    @FindBy(xpath = "//li[@class='select2-search-field']/input[@id='s2id_autogen1']")
    public WebElement selectField;

    @FindBy(xpath = "//div[@id='select2-drop']/ul")
    public WebElement selectFromDropdownList;               // group or branch from list

    @FindBy(xpath = "//input[@id='message_subject']")
    public WebElement subjectInputField;

    @FindBy(xpath = "//div[@class='note-editable tl-message-editor span9']")
    public WebElement messageInputField;

    @FindBy(xpath = "//a[@id='tl-attachment']")
    public WebElement selectAttachmentForMsg;

    @FindBy(xpath = "//input[@id='submit_send_message']")
    public WebElement sendMessageBtn;

    @FindBy(xpath = "//a[text()='Cancel']")
    public WebElement cancelBTN;

    @FindBy(xpath = "//div[@class='tl-table-operations-trigger touchable']/div")
    public WebElement options;

    /**
     * @return Возвращает страницу AddUserPage для регистрации пользователя
     */
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUser);
        return new AddUserPage();
    }

    public void showMassActionDropdownMenu(Action action) {
        WebElement actionElement = actionsDropdownMenu.findElement(By.xpath(".//a[normalize-space()='" + action + "']"));
        actionElement.click();
    }

//    // Нужно связать со списком из раздела Branch и добавить в метод performMassAction
//    public void selectBranchFromDropdownList() {
//        WebElement branchElement = selectFromDropdownList.findElement(By.xpath("//*[@id='select2-drop']/descendant::*[contains(text(),'" + branchName + "')]"));
//        branchElement.click();
//    }
//
//    // Нужно связать со списком из раздела Groups и добавить в метод performMassAction
//    public void selectGroupFromDropdownList() {
//        WebElement groupElement = selectFromDropdownList.findElement(By.xpath("//*[@id='select2-drop']/descendant::*[contains(text(),'" + groupName + "')]"));
//        groupElement.click();
//    }

    public UserPage performMassAction(Action action) {
        webElementActions.click(checkboxTick).click(massActionsBtn);
        showMassActionDropdownMenu(action);
        if (action.equals(Action.DELETE) || action.equals(Action.ACTIVATE) || action.equals(Action.DEACTIVATE)) {
            webElementActions.click(submitButton);
        } else if (action.equals(Action.ADD_TO_BRANCH) || action.equals(Action.REMOVE_FROM_BRANCH)) {
            webElementActions.click(selectField);
//            selectBranchFromDropdownList();
        } else if (action.equals(Action.ADD_TO_GROUP) || action.equals(Action.REMOVE_FROM_GROUP)) {
            webElementActions.click(selectField);
//            selectGroupFromDropdownList();
        } else if (action.equals(Action.SEND_MESSAGE)) {
            webElementActions.sendKeys(subjectInputField, "Meeting")
                    .sendKeys(messageInputField, "We are waiting for you today at 2pm")
                    .click(sendMessageBtn);
        }
        return this;
    }

    public UserPage cancelAction() {
        webElementActions.click(cancelBTN);
        return this;
    }

    public void selectOptions(WebElement optionName){
        WebElement optionElement = options.findElement(By.xpath(".//i[@alt='" + optionName + "'"));
        optionElement.click();
    }
}
