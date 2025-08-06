package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Comments {
    @Schema(description = "общее количество комментариев", example = "10")
    private Integer count;

    private List<Comment> results;
}
//Comments:
//type: object
//properties:
//count:
//type: integer
//format: int32
//description: 'общее количество комментариев'
//results:
//type: array
//items:
//$ref: '#/components/schemas/Comment'
