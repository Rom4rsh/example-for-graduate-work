package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public Ad addAds(MultipartFile image, CreateOrUpdateAd properties) {
        String fakeImageAd = "/image/fake-image.url";

        User fakeUser = new User();
        fakeUser.setId(1);
        fakeUser.setEmail("fake@mail.ru");
        fakeUser.setFirstName("Fake");
        fakeUser.setLastName("Fake");
        fakeUser.setPhone("+79271322273");
        fakeUser.setImage("/avatars/.");
        fakeUser.setRole(Role.USER);

        Ad ad = new Ad();
        ad.setPk(1);
        ad.setImage(fakeImageAd);
        ad.setAuthor(fakeUser.getId());
        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());

        return ad;
    }

    @Override
    public ExtendedAd getAds(Integer id) {
        // Заглушка: "поиск" объявления в БД
        if (id <= 0) {
            throw new NoSuchElementException("Ad not found");
        }

        //Фейковые данные
        ExtendedAd ad = new ExtendedAd();
        ad.setPk(id);
        ad.setTitle("Кофеварка");
        ad.setDescription("Почти новая кофеварка");
        ad.setPrice(3000);
        ad.setEmail("seller@example.com");
        ad.setPhone("+79998887766");
        ad.setAuthorFirstName("Иван");
        ad.setAuthorLastName("Иванов");
        ad.setImage("/images/ad_" + id + ".jpg");

        return ad;
    }

    @Override
    public Void removeAd(Integer id) {
        return null;
    }

    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd dto) {
        return new Ad();
    }

//    @Override
//    public Ads getAdsMe() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Находим пользователя по имени
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//
//        Ads??
//
//        return new Ads(adDtos.size(), adDtos);
//    }
}
