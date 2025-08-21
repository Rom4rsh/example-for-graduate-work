package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentsService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comments getCommentsByAdId(Integer adId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new NoSuchElementException("Ad not found"));
        List<CommentDto> comments = ad.getComments().stream()
                .map(commentMapper::toCommentDto)
                .collect(Collectors.toList());
        return new Comments(comments.size(), comments);
    }

    @Transactional
    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateComment commentDto, Authentication authentication) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new NoSuchElementException("Ad not found"));

        User author = getUserFromAuthentication(authentication);

        Comment comment = commentMapper.createComment(commentDto);
        comment.setAuthor(author);
        comment.setAd(ad);
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        Comment comment = getCommentIfExistsAndBelongsToAd(adId, commentId);
        commentRepository.delete(comment);
    }

    @Transactional
    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateComment commentDto) {
        Comment comment = getCommentIfExistsAndBelongsToAd(adId, commentId);

        if (commentDto.getText() != null && !commentDto.getText().isBlank()) {
            comment.setText(commentDto.getText());
        }

        return commentMapper.toCommentDto(commentRepository.save(comment));
    }

    private User getUserFromAuthentication(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    private Comment getCommentIfExistsAndBelongsToAd(Integer adId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));

        if (!comment.getAd().getId().equals(adId)) {
            throw new NoSuchElementException("Comment does not belong to the specified ad");
        }

        return comment;
    }
}
