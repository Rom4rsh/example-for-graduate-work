package ru.skypro.homework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

/**
 * Контроллер для работы с объявлениями.
 * <p>
 * Предоставляет CRUD-операции для объявлений:
 * получение всех объявлений, добавление нового, обновление, удаление,
 * получение объявлений текущего пользователя и обновление изображения объявления.
 */

@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final ObjectMapper objectMapper;

    public AdsController(AdsService adsService, ObjectMapper objectMapper) {
        this.adsService = adsService;
        this.objectMapper = objectMapper;
    }

    /**
     * Получает список всех объявлений.
     *
     * @return объект {@link Ads}, содержащий все объявления
     */
    @Operation(summary = "Получить все объявления",
            description = "Возвращает список объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список объявлений",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ads.class)
                            )
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Ads getAllAds() {
        return adsService.getAllAds();
    }
    /**
     * Создаёт новое объявление с изображением.
     *
     * @param image изображение для объявления
     * @param propertiesJson JSON-строка с данными объявления ({@link CreateOrUpdateAd})
     * @param authentication данные об авторизованном пользователе
     * @return объект {@link AdDto}, представляющий созданное объявление
     * @throws JsonProcessingException если возникла ошибка обработки JSON
     * @throws IOException если возникла ошибка при сохранении изображения
     */
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdDto> addAd(
            @RequestPart("image") MultipartFile image,
            @RequestPart("properties") String propertiesJson,
            Authentication authentication
    ) throws JsonProcessingException, IOException {
        CreateOrUpdateAd properties = objectMapper.readValue(propertiesJson, CreateOrUpdateAd.class);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adsService.addAds(image, properties, authentication));
    }

    /**
     * Получает расширенную информацию об объявлении по его идентификатору.
     *
     * @param id уникальный идентификатор объявления
     * @return объект {@link ExtendedAd} с полной информацией об объявлении
     */
    @Operation(summary = "Получение информации об объявлении",
                responses = {
                        @ApiResponse(responseCode = "200", description = "OK"),
                        @ApiResponse(responseCode = "404", description = "Not Found")
                })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExtendedAd getAds(@PathVariable Integer id) {
        return adsService.getExtendedAd(id);
    }
    /**
     * Удаляет объявление по идентификатору.
     *
     * @param id уникальный идентификатор объявления
     */
    @Operation(summary = "Удаление объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAd(@PathVariable Integer id) throws IOException {
        adsService.removeAd(id);
    }
    /**
     * Обновляет данные объявления.
     *
     * @param id идентификатор обновляемого объявления
     * @param updateAd объект {@link CreateOrUpdateAd}, содержащий новые данные объявления
     * @return объект {@link AdDto}, представляющий обновлённое объявление
     */
    @Operation(summary = "Обновление информации об объявлении",
            responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdDto updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAd updateAd) {
        AdDto updatedAdDto = adsService.updateAd(id, updateAd);
        return updatedAdDto;
    }
    /**
     * Получает все объявления текущего авторизованного пользователя.
     *
     * @param authentication данные об авторизованном пользователе
     * @return объект {@link Ads}, содержащий объявления текущего пользователя
     */
    @Operation(summary = "Получение объявлений авторизованного пользователя",
            responses = @ApiResponse(responseCode = "200",
            description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))))
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Ads getAdsMe(Authentication authentication) {
        return adsService.getAdsMe(authentication);
    }
    /**
     * Обновляет изображение объявления.
     *
     * @param image новое изображение объявления
     * @param id идентификатор объявления, для которого обновляется изображение
     * @return массив байт с новым изображением
     * @throws IOException если возникла ошибка при обработке изображения
     */
    @Operation(summary = "Обновление картинки объявления",
            responses = @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = MediaType.IMAGE_PNG_VALUE)))
    @PatchMapping(value ="/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] updateImage(@RequestPart MultipartFile image, @PathVariable Integer id) throws IOException {
            byte[] updateImage = adsService.updateImage(id, image);
            return updateImage;
    }
}