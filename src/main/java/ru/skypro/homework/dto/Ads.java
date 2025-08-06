package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ads {

    @Schema(description = "общее количество объявлений", example = "15")
    private Integer count;

    @Schema(description = "список объявлений")
    private List<Ad> results;

}

