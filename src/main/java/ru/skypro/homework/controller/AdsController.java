package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AdsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private final AdsService adsService;

    @GetMapping
    ResponseEntity<Ads> getAllAds(){
        adsService.getAllAds();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавление объявления")
    @PostMapping
    ResponseEntity<Ad> addAds(@RequestParam MultipartFile image, @RequestBody CreateOrUpdateComment properties){
        return null;
    }
}
//
//post:
//tags:
//        - Объявления
//summary: 'Добавление объявления'
//operationId: addAd
//requestBody:
//content:
//multipart/form-data:
//schema:
//required:
//        - image
//                - properties
//type: object
//properties:
//properties:
//$ref: '#/components/schemas/CreateOrUpdateAd'
//image:
//type: string
//format: binary
//responses:
//        '201':
//description: Created
//content:
//application/json:
//schema:
//$ref: '#/components/schemas/Ad'
//        '401':
//description: Unauthorized

