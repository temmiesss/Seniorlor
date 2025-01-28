package page.dashboard;

import drivers.Driver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.BasePage;
import page.login.LoginPage;
import page.users.AddUserPage;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//div[@class='hidden-phone']/a[normalize-space()='Add user']")
    public WebElement addUserBtn;

    @FindBy(xpath = "//span[@class='arrow-down']")
    public WebElement subMenu;

    @FindBy(xpath = "//a[@data-testid='legacy-menu-item']")
    public WebElement legacyMenuItem;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Users']")
    public WebElement usersSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Courses']")
    public WebElement coursesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Categories']")
    public WebElement categoriesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Groups']")
    public WebElement groupsSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Branches']")
    public WebElement branchesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Events engine']")
    public WebElement eventsEngineSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='User types']")
    public WebElement userTypesSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Import - Export']")
    public WebElement importExportSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Reports']")
    public WebElement reportsSection;

    @FindBy(xpath = "//div[@class='tl-bold-link']/a[normalize-space()='Account & Settings']")
    public WebElement AccountAndSettingsSection;

    /**
     * @return Возвращает объект типа LoginPage, позволяя продолжить работу с этой страницей.
     * @author Akylai
     * Метод ожидает и открывает меню и переключается на  Legacy Interface
     */
    public LoginPage switchToLegacyInterface() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(subMenu));
            webElementActions.click(subMenu);

            wait.until(ExpectedConditions.visibilityOf(legacyMenuItem));
            webElementActions.click(legacyMenuItem);

            return new LoginPage();
        } catch (NoSuchElementException e) {
            System.err.println("Element not found or not clickable: " + e.getMessage());
            return new LoginPage();
        }
    }
    /**
     * @author Akylai
     */

    public void navigateToSection(String sectionName) {
        WebElement section;
        switch (sectionName.toLowerCase()) {
            case "users":
                section = usersSection;
                break;
            case "courses":
                section = coursesSection;
                break;
            case "categories":
                section = categoriesSection;
                break;
            case "groups":
                section = groupsSection;
                break;
            case "branches":
                section = branchesSection;
                break;
            case "events engine":
                section = eventsEngineSection;
                break;
            case "user types":
                section = userTypesSection;
                break;
            case "import - export":
                section = importExportSection;
                break;
            case "reports":
                section = reportsSection;
                break;
            case "account & settings":
                section = AccountAndSettingsSection;
                break;
            default:
                throw new IllegalArgumentException("Section not found: " + sectionName);
        }
        section.click();
    }

    /**
     * @author Akylai
     * @return Переход к добавлению пользователя со страницы dashboard в AddUserPage
     */
    public AddUserPage navigateToAddUserPage() {
        webElementActions.click(addUserBtn);
        return new AddUserPage();
    }
}
