package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toCommentDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    Comment toComment(CommentDto commentDto);
}
