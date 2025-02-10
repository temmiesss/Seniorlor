package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
public class CVSGenerator extends BasePage {

    @FindBy(xpath = "//i[@class='icon-download tl-icon19']")
    public WebElement saveCSV;

    public CVSGenerator saveFile(){
        webElementActions.click(saveCSV);
        return this;
    }
        public  void generateCsvFile(String fileName, List<String[]> data) {
            try {
                // Указываем где сохраняем файл / путь к папке resources/file/
                String resourcePath = "src/main/resources/file/";
                File directory = new File(resourcePath);

                // Создаём папку, если её нет / Проверяем существует ли папка resources/file/
                // если нет создает ее с помощью mkdirs
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Полный путь к файлу
                File file = new File(resourcePath + fileName);

                // Записываем данные в CSV
                try (FileWriter writer = new FileWriter(file)) {
                    for (String[] row : data) {
                        writer.append(String.join(",", row));
                        writer.append("\n");
                    }
                }

                // выводит на консоль файл CSV
                System.out.println("CSV файл успешно создан: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Ошибка при создании CSV файла: " + e.getMessage());
            } // если произошла ошибка тогда выводит сообщение в консоль
        }

    }

