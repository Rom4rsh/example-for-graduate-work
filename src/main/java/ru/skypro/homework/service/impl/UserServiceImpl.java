package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    @Transactional
    @Override
    public void setPassword(NewPassword dto, Authentication authentication) {

    }

    @Override
    public UserDto getCurrentUser(Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        return userMapper.toUserDto(user) ;
    }

    @Transactional
    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);

        userMapper.updateUserFromDto(updateUser, user);

        userRepository.save(user);

        return updateUser;
    }

    @Transactional
    @Override
    public byte[] updateUserImage(MultipartFile image, Authentication authentication) throws IOException {
        User user = getUserFromAuthentication(authentication);

        if (user.getImagePath() != null) {
            imageService.deleteImage(user.getImagePath());
        }

        String imagePath = imageService.saveUserImage(image);
        user.setImagePath(imagePath);
        userRepository.save(user);
        return image.getBytes();
    }

    private User getUserFromAuthentication(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
