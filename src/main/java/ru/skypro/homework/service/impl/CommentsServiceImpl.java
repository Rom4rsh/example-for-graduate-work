package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Override
    public Comments getCommentsByAdId(Integer adId) {
        return null;
    }

    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateComment comment, Authentication auth) {
        return null;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {

    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        return null;
    }
}
