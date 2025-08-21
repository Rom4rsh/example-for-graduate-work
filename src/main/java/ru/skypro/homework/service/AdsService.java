package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;

public interface AdsService {

    /**
     * Возвращает список всех объявлений.
     *
     * @return объект {@link Ads}, содержащий коллекцию всех доступных объявлений
     */
    Ads getAllAds();

    /**
     * Создаёт новое объявление.
     *
     * @param image изображение для объявления
     * @param properties DTO с данными объявления (заголовок, описание, цена и т.д.)
     * @param authentication данные об авторизованном пользователе, который создаёт объявление
     * @return объект {@link AdDto}, представляющий созданное объявление
     * @throws IOException если возникла ошибка при сохранении изображения
     */
    AdDto addAds(MultipartFile image, CreateOrUpdateAd properties, Authentication authentication) throws IOException;

    /**
     * Возвращает подробную информацию об объявлении по его идентификатору.
     *
     * @param id уникальный идентификатор объявления
     * @return объект {@link ExtendedAd} с расширенной информацией (включая описание, автора и пр.)
     */
    ExtendedAd getExtendedAd(Integer id);

    /**
     * Удаляет объявление по идентификатору.
     *
     * @param id уникальный идентификатор объявления
     */
    void removeAd(Integer id) throws IOException;

    /**
     * Обновляет данные объявления.
     *
     * @param id идентификатор обновляемого объявления
     * @param dto объект {@link CreateOrUpdateAd}, содержащий новые данные для объявления
     * @return обновлённый объект {@link AdDto}
     */
    AdDto updateAd(Integer id, CreateOrUpdateAd dto);

    /**
     * Возвращает список объявлений текущего пользователя.
     *
     * @param authentication данные об авторизованном пользователе
     * @return объект {@link Ads}, содержащий объявления, созданные текущим пользователем
     */
    Ads getAdsMe(Authentication authentication);

    /**
     * Обновляет изображение для объявления.
     *
     * @param id идентификатор объявления
     * @param image новое изображение
     * @return массив байт, представляющий обновлённое изображение
     * @throws IOException если возникла ошибка при обработке изображения
     */
    byte[] updateImage(Integer id, MultipartFile image) throws IOException;

}
