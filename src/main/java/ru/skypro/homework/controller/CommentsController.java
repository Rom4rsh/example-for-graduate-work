package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequiredArgsConstructor
public class CommentsController {

    @Operation(summary = "Получение комментариев объявления")
    @GetMapping
    ResponseEntity<>

}
