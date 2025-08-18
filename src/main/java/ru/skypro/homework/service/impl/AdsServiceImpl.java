package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Override
    public Ads getAllAds() {
        List<AdDto> adDtos = adRepository.findAll().stream()
                .map(adMapper::toAdDto).
                collect(Collectors.toList());
        return new Ads(adDtos.size(), adDtos);
    }

    @Transactional
    @Override
    public AdDto addAds(MultipartFile image, CreateOrUpdateAd properties, Authentication authentication) throws IOException{
        User author = getUserFromAuthentication(authentication);

        Ad ad = new Ad();
        ad.setAuthor(author);
        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());
        ad.setDescription(properties.getDescription());

        updateImage(ad.getId(), image);

        return adMapper.toAdDto(adRepository.save(ad));
    }

    @Override
    public ExtendedAd getExtendedAd(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ad not found"));

        return adMapper.toExtendedAd(ad);
    }

    @Override
    public void removeAd(Integer id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ad not found"));
        adRepository.delete(ad);
    }

    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAd dto) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ad not found"));

        if (dto.getTitle() != null) {
            ad.setTitle(dto.getTitle());
        }
        if (dto.getPrice() != null) {
            ad.setPrice(dto.getPrice());
        }
        if (dto.getDescription() != null) {
            ad.setDescription(dto.getDescription());
        }
        return adMapper.toAdDto(adRepository.save(ad));
    }

    @Override
    public Ads getAdsMe(Authentication authentication) {
        User author = getUserFromAuthentication(authentication);

        List<AdDto> adDtos = adRepository.findAllByAuthor(author).stream()
                .map(adMapper::toAdDto)
                .collect(Collectors.toList());
        return new Ads(adDtos.size(), adDtos);
    }

    @Transactional
    @Override
    public byte[] updateImage(Integer id, MultipartFile image) throws IOException {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ad not found"));

        if (ad.getImagePath() != null) {
            imageService.deleteImage(ad.getImagePath());
        }

        String imagePath = imageService.saveAdImage(image);
        ad.setImagePath(imagePath);
        adRepository.save(ad);

        return image.getBytes();
    }

    private User getUserFromAuthentication(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
