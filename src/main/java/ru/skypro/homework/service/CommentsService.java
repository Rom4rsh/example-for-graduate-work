package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentsService {
    /**
     * Получает все комментарии для указанного объявления.
     *
     * @param adId уникальный идентификатор объявления
     * @return объект {@link Comments}, содержащий список комментариев для данного объявления
     */
    Comments getCommentsByAdId(Integer adId);
    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId уникальный идентификатор объявления, к которому добавляется комментарий
     * @param comment объект {@link CreateOrUpdateComment}, содержащий текст и другие данные комментария
     * @param auth данные об авторизованном пользователе, который оставляет комментарий
     * @return объект {@link CommentDto}, представляющий созданный комментарий
     */
    CommentDto addComment(Integer adId, CreateOrUpdateComment comment, Authentication auth);
    /**
     * Удаляет комментарий по идентификаторам объявления и комментария.
     *
     * @param adId уникальный идентификатор объявления, к которому принадлежит комментарий
     * @param commentId уникальный идентификатор комментария
     */
    void deleteComment(Integer adId, Integer commentId);
    /**
     * Обновляет существующий комментарий.
     *
     * @param adId уникальный идентификатор объявления, к которому принадлежит комментарий
     * @param commentId уникальный идентификатор комментария
     * @param comment объект {@link CreateOrUpdateComment}, содержащий новые данные комментария
     * @return объект {@link CommentDto}, представляющий обновлённый комментарий
     */
    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);
}
