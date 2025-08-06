package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrUpdateComment {
    @Schema(description = "заголовок объявления", minLength = 8, maxLength = 64, example = "отличное объявление")
    @NotBlank
    @Size(min = 8, max = 64)
    private String title;
}
