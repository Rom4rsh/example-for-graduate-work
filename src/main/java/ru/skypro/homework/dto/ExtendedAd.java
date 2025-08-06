package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtendedAd {

    @Schema(description = "id объявления")
    private Integer pk;

    @Schema(description = "имя автора объявления",example = "Иван")
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления",example = "Иванов")
    private String authorLastName;

    @Schema(description = "описание объявления",example = "классная вещь")
    private String description;

    @Schema(description = "логин автора объявления",example = "user@example.com")
    private String email;

    @Schema(description = "ссылка на картинку автора объявления ")
    private String image;

    @Schema(description = "телефон автора объявления",example = "+79271732244")
    private String phone;

    @Schema(description = "цена объявления",example = "10000")
    private Integer price;

    @Schema(description = "заголовок объявления",example = "велосипед")
    private String title;



}

//ExtendedAd:
//type: object
//properties:
//pk:
//type: integer
//format: int32
//description: 'id объявления'
//authorFirstName:
//type: string
//description: 'имя автора объявления'
//authorLastName:
//type: string
//description: 'фамилия автора объявления'
//description:
//type: string
//description: 'описание объявления'
//email:
//type: string
//description: 'логин автора объявления'
//image:
//type: string
//description: 'ссылка на картинку объявления'
//phone:
//type: string
//description: 'телефон автора объявления'
//price:
//type: integer
//format: int32
//description: 'цена объявления'
//title:
//type: string
//description: 'заголовок объявления'
