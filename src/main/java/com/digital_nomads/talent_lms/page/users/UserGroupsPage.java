package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Akylai
 */
public class UserGroupsPage extends BasePage {

    @FindBy(xpath = "//i[@title='Remove from group']")
    public WebElement removeFromGroupOption;

    @FindBy(xpath = "//i[@title='Add to group']")
    public WebElement addToGroupOption;

    @FindBy(xpath = "//span[@class='label label-registration']")
    public WebElement registrationLabel;

    /**
     * Предназначен для клика по опции действия на определенной группе на веб-странице.
     *
     * @param groupName    - название группы
     * @param actionOption - действие из выпадающего списка MassAction
     */
    private void clickGroupOption(String groupName, WebElement actionOption) {
        try {
            WebElement groupElement = driver.findElement(By.xpath("//span[@title='" + groupName + "']"));
            webElementActions.moveToElement(groupElement).click(actionOption);
        } catch (NoSuchElementException e) {
            System.out.println("Group '" + groupName + "' is not found");
        }
    }

    /**
     * Предназначен для добавления пользователя в группу
     *
     * @param groupName - название группы
     * @return возвращает текущую страницу
     */
    public UserGroupsPage addUserToGroup(String groupName) {
        clickGroupOption(groupName, addToGroupOption);
        return this;
    }

    /**
     * Предназначен для удаления пользователя из группы
     *
     * @param groupName название группы
     * @return возвращает текущую страницу
     */
    public UserGroupsPage removeUserFromGroup(String groupName) {
        clickGroupOption(groupName, removeFromGroupOption);
        return this;
    }

}
