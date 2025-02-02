import org.testng.Assert;
import org.testng.annotations.Test;

public class UserPageTest extends BaseTest{

    /**
     * @author Akylai
     */

    @Test
    public void testNavigateToAddUserPageFromUserPage(){
        addUserPage = userPage.navigateToAddUserPage();
        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");
    }



}
