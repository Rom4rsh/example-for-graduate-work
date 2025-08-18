package ru.skypro.homework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import javax.validation.Valid;
import java.io.IOException;


//@Slf4j
//@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final ObjectMapper objectMapper;

    public AdsController(AdsService adsService, ObjectMapper objectMapper) {
        this.adsService = adsService;
        this.objectMapper = objectMapper;
    }

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

    @Operation(summary = "Удаление объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
    }

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
// /ads/{id}:
//get:
//tags:
//        - Объявления
//summary: 'Получение информации об объявлении'
//operationId: getAds
//parameters:
//        - name: id
//in: path
//required: true
//schema:
//type: integer
//format: int32
//responses:
//        '200':
//description: OK
//content:
//application/json:
//schema:
//$ref: '#/components/schemas/ExtendedAd'
//        '401':
//description: Unauthorized
//        '404':
//description: Not found
//


