package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Schema(description = "id автора комментария", example = "1234")
    private Integer author;

    @Schema(description = "ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(description = "имя создателя комментария", example = "Вася")
    private String authorFirstName;

    @Schema(description = "дата и время создания комментария", example = "00:00:00 01.01.1970", format = "int64",type = "integer")
    private Instant createdAt;

    @Schema(description = "id комментария", example = "1234")
    private Integer pk;

    @Schema(description = "текст комментария", example = "отличный товар")
    private String text;
}


//createdAt:
//type: integer
//format: int64??
//description: 'дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970'????
//pk:
//type: integer
//format: int32??
//description: 'id комментария'


