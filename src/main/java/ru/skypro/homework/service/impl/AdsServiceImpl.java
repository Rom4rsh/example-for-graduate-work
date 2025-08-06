package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    @Override
    public Ads getAllAds() {
        List<Ad> ads = List.of(
                new Ad(1, "/images/1.jpg", 1234, 10000, "велосипед"),
                new Ad(2, "/images/1.jpg", 1234, 100000, "квадроцикл")

        );
        return new Ads(ads.size(), ads);
    }
}
