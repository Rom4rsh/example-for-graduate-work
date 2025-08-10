package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdsService {

    Ads getAllAds();

    AdDto addAds(MultipartFile image, CreateOrUpdateAd properties);

    ExtendedAd getAds(Integer id);

    Void removeAd(Integer id);

    AdDto updateAds(Integer id, CreateOrUpdateAd dto);

    Ads getAdsMe();

    byte[] updateImage(Integer id, MultipartFile image);

}
