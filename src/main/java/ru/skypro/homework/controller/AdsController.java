package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import java.util.NoSuchElementException;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "Получение всех объявлений")
    @GetMapping
    ResponseEntity<Ads> getAllAds() {
        adsService.getAllAds();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавление объявления")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Ad> addAds(@RequestPart MultipartFile image, @RequestPart CreateOrUpdateAd properties) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAds(image, properties));
    }

    @Operation(summary = "Получение информации об объявлении")
    @GetMapping("/{id}")
    ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        try {
            ExtendedAd ad = adsService.getAds(id);
            return ResponseEntity.ok(ad);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Удаление объявления")
    @DeleteMapping("{/id}")
    ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        try {
            adsService.removeAd(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Обновление информации об объявлении")
    @PatchMapping("{/id}")
    ResponseEntity<Ad> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAd updateAd) {
        try {
            Ad updatedAd = adsService.updateAds(id, updateAd);
            return ResponseEntity.ok(updatedAd);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @GetMapping("/me")
    ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(adsService.getAdsMe());
    }

    @Operation(summary = "Обновление картинки пользователя")
    @PatchMapping("/{id}/image")
    ResponseEntity<byte[]> updateImage(@RequestPart MultipartFile image, @PathVariable Integer id) {

        try {
            byte[] updateImage = adsService.updateImage(id, image);
            return ResponseEntity.ok(updateImage);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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


