package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    @Override
    public Ads getAllAds() {
        List<AdDto> adDtos = List.of(
                new AdDto(1, "/images/1.jpg", 1234, 10000, "велосипед"),
                new AdDto(2, "/images/1.jpg", 1234, 100000, "квадроцикл")
        );
        return new Ads(adDtos.size(), adDtos);
    }

    @Override
    public AdDto addAds(MultipartFile image, CreateOrUpdateAd properties) {
        String fakeImageAd = "/image/fake-image.url";

        UserDto fakeUserDto = new UserDto();
        fakeUserDto.setId(1);
        fakeUserDto.setEmail("fake@mail.ru");
        fakeUserDto.setFirstName("Fake");
        fakeUserDto.setLastName("Fake");
        fakeUserDto.setPhone("+79271322273");
        fakeUserDto.setImage("/avatars/.");
        fakeUserDto.setRole(Role.USER);

        AdDto adDto = new AdDto();
        adDto.setPk(1);
        adDto.setImage(fakeImageAd);
        adDto.setAuthor(fakeUserDto.getId());
        adDto.setTitle(properties.getTitle());
        adDto.setPrice(properties.getPrice());

        return adDto;
    }

    @Override
    public ExtendedAd getAds(Integer id) {
        // Заглушка: "поиск" объявления в БД
        if (id <= 0) {
            throw new AdNotFoundException("Объявление не найдено");
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
    public AdDto updateAds(Integer id, CreateOrUpdateAd dto) {
        return new AdDto();
    }

    @Override
    public Ads getAdsMe() {
        return null;
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile image) {
        return new byte[0];
    }


//    @Override
//    public Ads getAdsMe() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Находим пользователя по имени
//        UserDto user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//
//        Ads??
//
//        return new Ads(adDtos.size(), adDtos);
//    }
}
