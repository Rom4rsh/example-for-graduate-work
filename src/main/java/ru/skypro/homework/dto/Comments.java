package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comments {
    @Schema(description = "общее количество комментариев", example = "10")
    private Integer count;

    private List<CommentDto> results;
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
//$ref: '#/components/schemas/CommentDto'
