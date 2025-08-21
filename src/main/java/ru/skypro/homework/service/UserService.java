package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {

    /**
     * Обновляет пароль текущего пользователя.
     *
     * @param dto объект {@link NewPassword}, содержащий новый пароль
     * @param authentication данные об авторизованном пользователе
     */
    void setPassword(NewPassword dto, Authentication authentication);

    /**
     * Получает информацию о текущем пользователе.
     *
     * @param authentication данные об авторизованном пользователе
     * @return объект {@link UserDto}, содержащий данные текущего пользователя
     */
    UserDto getCurrentUser(Authentication authentication);

    /**
     * Обновляет данные текущего пользователя.
     *
     * @param updateUser объект {@link UpdateUser}, содержащий новые данные пользователя
     * @param authentication данные об авторизованном пользователе
     * @return объект {@link UpdateUser}, представляющий обновлённые данные пользователя
     */
    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);

    /**
     * Обновляет изображение профиля текущего пользователя.
     *
     * @param image новое изображение пользователя
     * @param authentication данные об авторизованном пользователе
     * @return массив байт, представляющий обновлённое изображение
     * @throws IOException если возникла ошибка при сохранении изображения
     */
    byte[] updateUserImage(MultipartFile image, Authentication authentication) throws IOException;
}
