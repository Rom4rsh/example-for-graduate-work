package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateAdRequest {
    @Schema(type = "string", format = "binary", description = "Изображение")
    private MultipartFile image;

    @Schema(description = "Свойства объявления в JSON")
    private CreateOrUpdateAd properties;
}
