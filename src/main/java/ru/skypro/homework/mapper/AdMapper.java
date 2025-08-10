package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {
    AdDto toAdDto(Ad ad);

    @Mapping(target = "id", ignore = true)
    Ad toAd(AdDto adDto);
}
