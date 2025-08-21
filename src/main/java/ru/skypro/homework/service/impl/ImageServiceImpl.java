package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${image.upload.directory}")
    private String uploadDirectory;

    @Override
    public String saveAdImage(MultipartFile image) throws IOException {
        return saveImage(image, "adsImage");
    }

   @Override
    public String saveUserImage(MultipartFile image) throws IOException {
        return saveImage(image, "usersImage");
    }

    @Override
    public String saveImage(MultipartFile image, String subdirectory) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        String originalFilename = image.getOriginalFilename();
        assert originalFilename != null;
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID() + fileExtension;

        Path uploadPath = Paths.get(uploadDirectory, subdirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(image.getInputStream(), filePath);

        return "/" + Paths.get(subdirectory, uniqueFilename).toString().replace("\\", "/");
    }


    public byte[] getImage(String imagePath) throws IOException {
        Path fullPath = Paths.get(uploadDirectory, imagePath);
        if (!Files.exists(fullPath)) {
            throw new IOException("Image not found");
        }
        return Files.readAllBytes(fullPath);
    }


    public void deleteImage(String imagePath) throws IOException {
        Path fullPath = Paths.get(uploadDirectory, imagePath.substring(1).replace("/","\\"));
        if (Files.exists(fullPath)) {
            Files.delete(fullPath);
        }
    }
}
