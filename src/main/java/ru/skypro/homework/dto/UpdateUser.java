package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {
    @Schema(description = "имя пользователя ", minLength = 3, maxLength = 10, example = "Иван")
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя ", minLength = 3, maxLength = 10, example = "Иванов")
    @Size(min = 3, max = 10)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя", example = "+7(927)1732200")
    private String phone;
}
//
//UpdateUser:
//type: object
//properties:
//firstName:
//type: string
//description: 'имя пользователя'
//minLength: 3
//maxLength: 10
//lastName:
//type: string
//description: 'фамилия пользователя'
//minLength: 3
//maxLength: 10
//phone:
//type: string
//description: 'телефон пользователя'
//pattern: '\+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2}'
