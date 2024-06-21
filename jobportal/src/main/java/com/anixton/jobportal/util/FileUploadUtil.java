package com.anixton.jobportal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    /**
     * Saves a file to the specified upload directory.
     *
     * @param uploadDir the upload directory path
     * @param fileName the name of the file to be saved
     * @param multipartFile the multipart file to be saved
     * @throws IOException if an I/O error occurs during file operations
     */
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream();){
            Path path = uploadPath.resolve(fileName);
            System.out.println("FilePath " + path);
            System.out.println("FileName " + fileName);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw   new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
