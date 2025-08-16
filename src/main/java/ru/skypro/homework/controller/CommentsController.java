package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

import javax.validation.Valid;

//@Slf4j
//@CrossOrigin(value = "http://localhost:3000")

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads/{id}/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Operation(
            summary = "Получить все комментарии объявления",
            description = "Возвращает список всех комментариев для указанного объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарии найдены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class))

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Comments getComments(@Parameter(description = "ID объявления", required = true) @PathVariable Integer id) {
        return commentsService.getCommentsByAdId(id);
    }

    @Operation(
            summary = "Добавить комментарий",
            description = "Добавляет новый комментарий к указанному объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Комментарий создан",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(
            @Parameter(description = "ID объявления", required = true) @PathVariable Integer id,
            @RequestBody @Valid CreateOrUpdateComment commentDto,
            Authentication authentication
    ) {
        return commentsService.addComment(id, commentDto, authentication);
    }

    @Operation(
            summary = "Удалить комментарий",
            description = "Удаляет комментарий по его ID из указанного объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Комментарий удален"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Нет прав для удаления комментария"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление или комментарий не найдены"
                    )
            }
    )
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @Parameter(description = "ID объявления", required = true) @PathVariable Integer id,
            @Parameter(description = "ID комментария", required = true) @PathVariable Integer commentId
    ) {
        commentsService.deleteComment(id, commentId);
    }

    @Operation(
            summary = "Обновить комментарий",
            description = "Обновляет текст указанного комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий обновлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Требуется аутентификация"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Нет прав для обновления комментария"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление или комментарий не найдены"
                    )
            }
    )
    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(
            @Parameter(description = "ID объявления", required = true) @PathVariable Integer id,
            @Parameter(description = "ID комментария", required = true) @PathVariable Integer commentId,
            @RequestBody @Valid CreateOrUpdateComment updatedComment
    ) {
        return commentsService.updateComment(id, commentId, updatedComment);
    }

}
