package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ad {

    @Schema(description = "id автора объявления",example = "1234")
    private Integer author;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    @Schema(description = "id объявления",example = "1234")
    private Integer pk;

    @Schema(description = "цена объявления")
    private Integer price;

    @Schema(description = "заголовок объявления",example = "чайник")
    private String title;
}
