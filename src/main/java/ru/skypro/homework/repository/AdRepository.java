package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.Collection;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {
    Collection<Ad> findAllByAuthor(User author);
}
