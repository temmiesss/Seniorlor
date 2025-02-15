package com.digital_nomads.talent_lms.page.users;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Akylai
 * Метод отработан на менторском уроке
 */
public class FileUtilsUser {
    /**
     * Этот метод помогает сохранить скачанный файл в папке ресурсов проекта,
     * обеспечивая создание необходимых директорий, если их нет.
     *
     * @param downloadedFile скачанный файл
     * @param fileName       название файла, который был загружен
     * @throws IOException метод может выбросить исключение типа IOException, если произойдут ошибки ввода/вывода
     */
    public void saveToResources(File downloadedFile, String fileName) throws IOException {
        // Определяем путь до папки resources
        Path resourcesPath = Paths.get("src", "main", "resources", "files");
        if (!Files.exists(resourcesPath)) {
            Files.createDirectories(resourcesPath);
            System.out.println("Directory " + resourcesPath.toAbsolutePath() + " is created");// Создаём папку, если её нет
        }
        // Полный путь, куда будем сохранять
        Path destinationPath = resourcesPath.resolve(fileName);
        // Копируем скачанный файл в папку resources
        Files.copy(downloadedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File saved to: " + destinationPath.toAbsolutePath());
    }

    private final String downloadDirectory = "C:\\Users\\User\\Downloads";

    /**
     * проверяет, существует ли файл с указанным именем (fileName)
     * в папке загрузок (downloadDirectory), и является ли он обычным файлом, а не директорией.
     *
     * @param fileName имя файла
     * @return возвращает true, если файл существует и не является директорией (через проверку file.exists() и !file.isDirectory())
     * Если файл не найден или является директорией, метод вернет false
     */
    public boolean isFileDownloaded(String fileName) {
        File file = new File(downloadDirectory + "/" + fileName);
        return file.exists() && !file.isDirectory(); // Проверяем, существует ли файл и это не директория
    }

    /**
     * помогает сохранить файл, только если он был загружен, и переместить его в нужную папку для дальнейшего использования.
     *
     * @param fileName имя файла
     * @throws IOException Метод бросает исключение IOException, если при сохранении файла возникает ошибка.
     */
    public void saveDownloadedFile(String fileName) throws IOException {
        File downloadedFile = new File(downloadDirectory + "/" + fileName);
        if (downloadedFile.exists()) {
            saveToResources(downloadedFile, fileName);
        }
    }
}
