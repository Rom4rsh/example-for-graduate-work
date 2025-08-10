package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void setPassword(NewPassword dto) {
    }

    @Override
    public UserDto getCurrentUser(Authentication authentication) {
        User user = getUserFromAuthentication(authentication);
        return userMapper.toUserDto(user) ;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, Authentication authentication) {
        User user = getUserFromAuthentication(authentication);

        userMapper.updateUserFromDto(updateUser, user);

        userRepository.save(user);

        return updateUser;
    }

    @Override
    public void updateUserImage(MultipartFile image) {

    }

    private User getUserFromAuthentication(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
