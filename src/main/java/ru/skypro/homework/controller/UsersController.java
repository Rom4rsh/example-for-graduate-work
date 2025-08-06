package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Operation(summary = "Обновление пароля")
    @PostMapping("/set_password")
    ResponseEntity<Void> setPassword(@RequestBody NewPassword password) {
        userService.setPassword(password);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile image) {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}


