package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

/**
 * Контроллер для работы с пользователями.
 * <p>
 * Предоставляет эндпоинты для изменения пароля, получения и обновления данных пользователя,
 * а также обновления аватара авторизованного пользователя.
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    /**
     * Обновляет пароль авторизованного пользователя.
     *
     * @param password объект {@link NewPassword}, содержащий текущий и новый пароли
     * @param authentication данные авторизованного пользователя
     */
    @Operation(summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пароль успешно изменен"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный запрос (некорректные данные)"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен (неверный текущий пароль)"
                    )
            })
    @PostMapping("/set_password")
    @ResponseStatus(HttpStatus.OK)
    public void setPassword(@RequestBody NewPassword password, Authentication authentication) {
        userService.setPassword(password, authentication);
    }
    /**
     * Получает информацию об авторизованном пользователе.
     *
     * @param authentication данные авторизованного пользователя
     * @return объект {@link UserDto} с информацией о пользователе
     */
    @Operation(summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о пользователе получена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    )
            })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(Authentication authentication) {
        return userService.getCurrentUser(authentication);
    }
    /**
     * Обновляет информацию об авторизованном пользователе.
     *
     * @param user объект {@link UpdateUser} с новыми данными пользователя
     * @param authentication данные авторизованного пользователя
     * @return объект {@link UpdateUser} с обновленной информацией
     */
    @Operation(summary = "Обновление информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Профиль успешно обновлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUser.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный запрос (некорректные данные)"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    )
            })
    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUser updateUser(@RequestBody UpdateUser user, Authentication authentication) {
        return userService.updateUser(user, authentication);
    }
    /**
     * Обновляет аватар авторизованного пользователя.
     *
     * @param image новое изображение аватара
     * @param authentication данные авторизованного пользователя
     * @throws IOException если произошла ошибка при обработке изображения
     */
    @Operation(summary = "Обновление аватара авторизованного пользователя",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Аватар успешно обновлен"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный запрос (неподдерживаемый формат изображения)"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    ),
                    @ApiResponse(
                            responseCode = "413",
                            description = "Размер файла слишком большой"
                    )
            })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUserImage(@RequestPart("image") MultipartFile image, Authentication authentication) throws IOException {
        userService.updateUserImage(image, authentication);
    }
}


