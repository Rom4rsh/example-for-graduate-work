package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    /**
     * Сохраняет изображение объявления.
     *
     * @param image файл изображения для сохранения
     * @return путь к сохранённому изображению или его идентификатор
     * @throws IOException если возникла ошибка при сохранении файла
     */
    String saveAdImage(MultipartFile image) throws IOException;
    /**
     * Сохраняет изображение пользователя.
     *
     * @param image файл изображения для сохранения
     * @return путь к сохранённому изображению или его идентификатор
     * @throws IOException если возникла ошибка при сохранении файла
     */
    String saveUserImage(MultipartFile image) throws IOException;
    /**
     * Сохраняет изображение в указанную поддиректорию.
     *
     * @param image файл изображения для сохранения
     * @param subdirectory поддиректория, куда нужно сохранить изображение
     * @return путь к сохранённому изображению
     * @throws IOException если возникла ошибка при сохранении файла
     */
    String saveImage(MultipartFile image, String subdirectory) throws IOException;
    /**
     * Получает изображение по пути.
     *
     * @param imagePath путь к изображению
     * @return массив байт, представляющий содержимое изображения
     * @throws IOException если файл не найден или возникла ошибка чтения
     */
    byte[] getImage(String imagePath) throws IOException;
    /**
     * Удаляет изображение по указанному пути.
     *
     * @param imagePath путь к изображению для удаления
     * @throws IOException если файл не найден или возникла ошибка при удалении
     */
    void deleteImage(String imagePath) throws IOException;
}
