package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Schema(description = "id пользователя",example = "123")
    private Integer id;

    @Schema(description = "логин пользователя",example = "user@example.com")
    private String email;

    @Schema(description = "имя",example = "Иван")
    private String firstName;

    @Schema(description = "фамилия пользователя",example = "Иванов")
    private String lastName;

    @Schema(description = "телефон пользователя",example = "+79271732293")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;

    @Schema(description = "ссылка на аватар")
    private String image;

}
