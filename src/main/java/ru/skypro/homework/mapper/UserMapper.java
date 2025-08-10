package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User fromUserDto(UserDto userDto);

    UpdateUser toUpdateUser(User user);

    @Mapping(target = "id", ignore = true)
    User fromUpdateUser(UpdateUser updateUser);

    void updateUserFromDto(UpdateUser updateUser, @MappingTarget User user);
}
