package com.digital_nomads.talent_lms.page.course;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
public class FileUtils {
    /**
     * Method for downloading files
     * @param downloadedFile
     * @param fileName
     * @throws IOException
     */
    public  void saveToResources(File downloadedFile, String fileName) throws IOException {
        // Определяем путь до папки resources
        Path resourcesPath = Paths.get("src", "main", "resources"); // или "test" для тестов
        if (!Files.exists(resourcesPath)) {
            Files.createDirectories(resourcesPath);
        }
        // Полный путь, куда будем сохранять
        Path destinationPath = resourcesPath.resolve(fileName);
        // Копируем скачанный файл в папку resources
        Files.copy(downloadedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Файл сохранен в: " + destinationPath.toAbsolutePath());
    }
}