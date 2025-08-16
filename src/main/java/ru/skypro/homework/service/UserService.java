package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {

    void setPassword(NewPassword dto, Authentication authentication);

    UserDto getCurrentUser(Authentication authentication);

    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);

    byte[] updateUserImage(MultipartFile image, Authentication authentication) throws IOException;
}
