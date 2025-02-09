package com.digital_nomads.talent_lms.page.users;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Akylai
 * Сортировка UserPage таблицы
 */
public class SortingUserPage extends BasePage {

    /**
     * Выполняет сортировку таблицы по указанному столбцу
     *
     * @param columnName - название столбца
     */
    public void sortByColumn(String columnName) {
        WebElement header = driver.findElement(By.xpath("//th[contains(text(), '" + columnName + "') and contains(@class, 'sorting')]"));
        header.click();
        wait.until(ExpectedConditions.attributeContains(header, "class", "sorting_"));
    }

    /**
     * Извлекает данные из указанного столбца таблицы и возвращает их в виде списка строк
     *
     * @param columnIndex индекс столбца
     * @return возвращает список значений, которые находятся в ячейках указанного столбца таблицы (по индексу columnIndex).
     */
    public List<String> getColumnData(int columnIndex) {
        return driver.findElements(By.xpath("//table[@id='tl-users-grid']//tbody/tr/td[" + columnIndex + "]"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Проверяет, отсортирован ли список строк в порядке возрастания.
     *
     * @param list - список всех элементов
     * @return возвращает true, указывая на то, что список отсортирован без ошибок
     */
    public boolean isSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * проверяет, отсортирован ли список типов пользователей (например, роли пользователей) в порядке,
     * заданном в константном списке USER_TYPE_ORDER
     *
     * @param userTypes - доступные виды пользователя
     * @return возвращает true, указывая на то, что список отсортирован в правильном порядке
     */
    public boolean isSortedByUserType(List<String> userTypes) {
        final List<String> USER_TYPE_ORDER = List.of("SuperAdmin", "Admin-Type", "Trainer-Type", "Learner-Type");
        for (int i = 0; i < userTypes.size() - 1; i++) {
            int index1 = USER_TYPE_ORDER.indexOf(userTypes.get(i));
            int index2 = USER_TYPE_ORDER.indexOf(userTypes.get(i + 1));

            if (index1 > index2) {
                return false;
            }
        }
        return true;
    }

    /**
     * проверяет, отсортированы ли данные о регистрации пользователей по времени в порядке возрастания (от самой старой даты к самой новой).
     *
     * @param registrationData - даты регистрации для сортировки
     * @return Если все даты в списке упорядочены в порядке возрастания, метод возвращает true.
     */
    public boolean isSortedByRegistrationDate(List<String> registrationData) {
        // Преобразуем строковые данные в числа (дни, месяцы, годы)
        List<Long> registrationTimes = new ArrayList<>();

        for (String date : registrationData) {
            registrationTimes.add(convertToEpochTime(date));
        }
        for (int i = 1; i < registrationTimes.size(); i++) {
            if (registrationTimes.get(i) < registrationTimes.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * проверяет, отсортированы ли данные о последнем входе пользователей по времени в порядке возрастания
     *
     * @param lastLoginData - дата последнего логина, для соритровки
     * @return Если все временные метки упорядочены по возрастанию (то есть каждый следующий вход был позже предыдущего), метод возвращает true.
     */
    public boolean isSortedByLastLogin(List<String> lastLoginData) {
        List<Long> lastLoginTimes = new ArrayList<>();

        for (String date : lastLoginData) {
            lastLoginTimes.add(convertToEpochTime(date));
        }
        for (int i = 1; i < lastLoginTimes.size(); i++) {
            if (lastLoginTimes.get(i) < lastLoginTimes.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод для преобразования строковой даты в значение времени (в миллисекундах с начала эпохи)
     *
     * @param date - строка, представляющая дату в относительном формате (например, "5 hours ago").
     * @return возвращает количество миллисекунд, прошедших с 1 января 1970 года (начало эпохи Unix)
     * для конкретной даты, которая представлена в строковом формате относительно текущего времени.
     */
    private long convertToEpochTime(String date) {
        if (date.contains("hour")) {
            int hours = Integer.parseInt(date.split(" ")[0]);
            return System.currentTimeMillis() - TimeUnit.HOURS.toMillis(hours);
        } else if (date.contains("day")) {
            int days = Integer.parseInt(date.split(" ")[0]);
            return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days);
        } else if (date.contains("month")) {
            int months = Integer.parseInt(date.split(" ")[0]);
            return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(months * 30L);
        } else if (date.contains("year")) {
            int years = Integer.parseInt(date.split(" ")[0]);
            return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(years * 365L);
        }
        return 0;
    }

    /**
     *  Выполняет экспорт CSV-файла путем нажатия на кнопку скачивания, используя WebDriver.
     *  Используется в классе SortingUserPageTest.
     */
    public void exportCSV() {
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//i[@class='icon-download tl-icon19']")));
        downloadButton.click();
    }
}
