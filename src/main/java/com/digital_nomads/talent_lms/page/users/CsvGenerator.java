package com.digital_nomads.talent_lms.page.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Akylai
 * Метод создан на менторском уроке
 */
public class CsvGenerator {

    /**
     * Метод создает CSV файл по указанному имени и сохраняет его в папке src/main/resources/files/
     * @param fileName - имя файла, который принимает метод
     * @param data - данные в виде списка массивов строк
     */
    public  void generateCsvFile(String fileName, List<String[]> data) {
        try {
            // Указываем где сохраняем файл / путь к папке resources/files/
            String resourcePath = "src/main/resources/files/";
            File directory = new File(resourcePath);

            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Полный путь к файлу
            File file = new File(resourcePath + fileName);

            // Записываем данные в CSV
            try (FileWriter writer = new FileWriter(file)) {
                for (String[] row : data) {
                    writer.append(String.join(",", row)).append("\n");
                }
            }
            // выводит на консоль файл CSV
            System.out.println("CSV файл успешно создан: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при создании CSV файла: " + e.getMessage());
        } // если произошла ошибка тогда выводит сообщение в консоль
    }

}
