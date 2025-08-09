package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

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
    public void setPassword(@RequestBody NewPassword password) {
        userService.setPassword(password);
    }

    @Operation(summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о пользователе получена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    )
            })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public User getUser() {
        return userService.getCurrentUser();
    }

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
    public UpdateUser updateUser(@RequestBody UpdateUser user) {
        return userService.updateUser(user);
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя",
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
    @PatchMapping("/me/image")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserImage(@RequestPart("image") MultipartFile image) {
        userService.updateUserImage(image);
    }
}


