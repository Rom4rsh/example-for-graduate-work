package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentsService {
    Comments getCommentsByAdId(Integer adId);
    CommentDto addComment(Integer adId, CreateOrUpdateComment comment, Authentication auth);
    void deleteComment(Integer adId, Integer commentId);
    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);
}
