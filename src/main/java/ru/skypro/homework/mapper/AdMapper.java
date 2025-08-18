package ru.skypro.homework.mapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;

@Component
public class AdMapper {
    @Operation(
            summary = "Конвертировать Ad в AdDto",
            description = "Преобразует объект Ad в его DTO представление",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное преобразование",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdDto.class)
                            )
                    )
            }
    )
    public AdDto toAdDto(Ad ad) {
        AdDto dto = new AdDto();
        dto.setPk(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setPrice(ad.getPrice());
        dto.setImage(ad.getImagePath());
        if (ad.getAuthor() != null) {
            dto.setAuthor(ad.getAuthor().getId());
        }
        return dto;
    }

    @Operation(
            summary = "Конвертировать Ad в ExtendedAdDto",
            description = "Преобразует объект Ad в его расширенное DTO представление с дополнительными полями",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное преобразование",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExtendedAd.class)
                            )
                    )
            }
    )
    public ExtendedAd toExtendedAd(Ad ad) {
        ExtendedAd dto = new ExtendedAd();
        dto.setPk(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setPrice(ad.getPrice());
        dto.setDescription(ad.getDescription());
        dto.setImage(ad.getImagePath());

        if (ad.getAuthor() != null) {
            dto.setAuthorFirstName(ad.getAuthor().getFirstName());
            dto.setAuthorLastName(ad.getAuthor().getLastName());
            dto.setEmail(ad.getAuthor().getUsername());
            dto.setPhone(ad.getAuthor().getPhone());
        }
        return dto;
    }
}
