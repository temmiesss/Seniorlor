import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ReportCourseTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();
    }

    @Test
    public void reportPress() throws IOException {

        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.exportFileReport();

        Assert.assertTrue(reportCoursePage.exportFile.isEnabled(), "Кнопка экспорта все еще активна!");

    }

    @Test
    public void todayReportTest() {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.todayReport();

        Assert.assertFalse(reportCoursePage.todayPress.isSelected(), "Кнопка отчета за сегодня нажимается!");
    }

    @Test
    public void yesterdayReportTest() {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.yesterdayReport();

        Assert.assertFalse(reportCoursePage.yesterdayPress.isSelected(), "Кнопка отчета за вчера нажимается!");
    }

    @Test
    public void weekReportTest() {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.weekReport();

        Assert.assertFalse(reportCoursePage.weekPress.isSelected(), "Кнопка отчета за неделю нажимается!");
    }

    @Test
    public void monthReportTest() throws IOException {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.monthReport();

        Assert.assertFalse(reportCoursePage.monthPress.isSelected(), "Кнопка отчета за месяц нажимается!");
    }

    @Test
    public void usersReportTest() {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.userReport();

        Assert.assertTrue(true);
    }

    @Test
    public void yearUserReportTest(){
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.yearUserReport();
        Assert.assertFalse(reportCoursePage.yearPress.isSelected(), "отчет по юзеру за год загружается!");
    }

    @Test
    public void yearReportTest(){
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.yearReport();

        Assert.assertFalse(reportCoursePage.yearPress.isSelected(), "Кнопка отчета за год нажимается!");
    }
    @Test
    public void reportFileReport() throws IOException {

        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.exportFileReport();
        File csvFile = new File("C:\\Users\\tokto\\Downloads\\Course_O_Conner-Grant_6_report (8).xlsx");
        fileUtils.saveToResources(csvFile, "Course_O_Conner-Grant_6_report (8).xlsx");

        Assert.assertTrue(reportCoursePage.exportFile.isEnabled(), "Кнопка экспорта все еще активна!");
    }

    @Test
    public void reportFileMonth() throws IOException {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.monthReport();
        File csvFile = new File("C:\\Users\\tokto\\Downloads\\Course_Python-_654_report.xlsx");
        fileUtils.saveToResources(csvFile,"Course_Python-_654_report.xlsx");

        Assert.assertFalse(reportCoursePage.monthPress.isSelected(), "отчет за месяц загружен!");
    }
    @Test
    public void weekReportFileTest() throws IOException {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.weekReport();
        File csvFile = new File("C:\\Users\\tokto\\Downloads\\Course_Python-_654_report.xlsx");
        fileUtils.saveToResources(csvFile,"Course_Python-_654_report.xlsx");

        Assert.assertFalse(reportCoursePage.weekPress.isSelected(), "Отчет за неделю загружен!");
    }
    @Test
    public void yesterdayReportFileTest() throws IOException {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.yesterdayReport();
        File csvFile = new File("C:\\Users\\tokto\\Downloads\\Course_Python-_654_report.xlsx");
        fileUtils.saveToResources(csvFile,"Course_Python-_654_report.xlsx");

        Assert.assertFalse(reportCoursePage.yesterdayPress.isSelected(), "Отчет за вчера загружен!");
    }

    @Test
    public void todayReportFileTest() throws IOException{
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.todayReport();
        File csvFile = new File("C:\\Users\\tokto\\Downloads\\Course_Python-_654_report.xlsx");
        fileUtils.saveToResources(csvFile,"Course_Python-_654_report.xlsx");

        Assert.assertFalse(reportCoursePage.todayPress.isSelected(), "Отчет за сегодня загружен!");
    }

    @Test
    public void yearReportFileTest() throws IOException {
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.yearReport();
        File csvFile = new File("C:\\Users\\tokto\\Downloads\\Course_Python-_654_report.xlsx");
        fileUtils.saveToResources(csvFile,"Course_Python-_654_report.xlsx");

        Assert.assertFalse(reportCoursePage.yearPress.isSelected(), "Отчет за год по курсу скачан!");
    }
    @Test
    public void todayUserReportTest(){
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.todayUserReport();
        Assert.assertFalse(reportCoursePage.todayPress.isSelected(), "отчет по юзеру за день загружается!");
    }
    @Test
    public void yesterdayUserReportTest(){
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.yesterdayUserReport();
        Assert.assertFalse(reportCoursePage.yesterdayPress.isSelected(), "отчет по юзеру за вчера загружается!");
    }
    @Test
    public void weekUserReportTest(){
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.weekUserReport();
        Assert.assertFalse(reportCoursePage.weekPress.isSelected(), "отчет по юзеру за неделю загружается!");
    }
    @Test
    public void monthUserReportTest(){
        Course randomCourse = randomCourseGenerator.randomCourse();
        reportCoursePage.reportCoursePage(randomCourse);
        reportCoursePage.monthUserReport();
        Assert.assertFalse(reportCoursePage.monthPress.isSelected(), "отчет по юзеру за месяц загружается!");
    }
    @AfterMethod
    public void tearDown1() {
        driver.manage().deleteAllCookies();
    }

}
//18