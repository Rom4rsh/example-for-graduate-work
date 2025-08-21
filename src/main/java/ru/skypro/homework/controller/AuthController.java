package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

import java.util.Collections;
import java.util.Map;

/**
 * Контроллер аутентификации и регистрации пользователей.
 * <p>
 * Предоставляет эндпоинты для входа в систему и создания новой учетной записи.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Выполняет аутентификацию пользователя по логину и паролю.
     *
     * @param login объект {@link Login}, содержащий логин (email) и пароль
     * @return карту с токеном аутентификации, ключ: "token"
     */
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Проверка учетных данных пользователя и вход в систему",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная аутентификация",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Неверные учетные данные",
                            content = @Content()
                    )
            }
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> login(@RequestBody Login login) {
        String token = authService.login(login.getUsername(), login.getPassword());
        return Collections.singletonMap("token", token);
    }
    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param register объект {@link Register}, содержащий данные нового пользователя
     */
    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Создание новой учетной записи пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Пользователь успешно зарегистрирован",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные данные для регистрации",
                            content = @Content()
                    )
            }
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody Register register) {
        if (!authService.register(register)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
