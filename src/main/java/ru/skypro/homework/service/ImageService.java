package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveAdImage(MultipartFile image) throws IOException;
    String saveUserImage(MultipartFile image) throws IOException;
    String saveImage(MultipartFile image, String subdirectory) throws IOException;
    byte[] getImage(String imagePath) throws IOException;
    void deleteImage(String imagePath) throws IOException;
}
