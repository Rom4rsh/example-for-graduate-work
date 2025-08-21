package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comments findAllByAd(Ad ad);
}
