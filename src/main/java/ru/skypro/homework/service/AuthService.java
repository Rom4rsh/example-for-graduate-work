package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

public interface AuthService {
    /**
     * Выполняет вход пользователя в систему.
     *
     * @param userName имя пользователя (логин), используемое для аутентификации
     * @param password пароль пользователя в открытом виде
     * @return строка с токеном аутентификации (JWT), если вход успешен;
     */
    String login(String userName, String password);

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param register объект {@link Register}, содержащий данные для регистрации
     *                 (логин, пароль, e-mail и другую необходимую информацию)
     * @return true, если регистрация прошла успешно; false, если пользователь уже существует
     */
    boolean register(Register register);
}
