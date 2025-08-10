package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    void setPassword(NewPassword dto);

    UserDto getCurrentUser(Authentication authentication);

    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);

    void updateUserImage(MultipartFile image);

}
