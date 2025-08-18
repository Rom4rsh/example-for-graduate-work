package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;

public interface AdsService {

    Ads getAllAds();

    AdDto addAds(MultipartFile image, CreateOrUpdateAd properties, Authentication authentication) throws IOException;

    ExtendedAd getExtendedAd(Integer id);

    void removeAd(Integer id);

    AdDto updateAd(Integer id, CreateOrUpdateAd dto);

    Ads getAdsMe(Authentication authentication);

    byte[] updateImage(Integer id, MultipartFile image) throws IOException;

}
