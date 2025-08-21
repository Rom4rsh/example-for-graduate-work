package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Сущность пользователя системы.
 * <p>
 * Содержит личные данные пользователя, логин и пароль для аутентификации,
 * роль, путь к аватару, а также связанные объявления и комментарии.
 * Реализует интерфейс {@link UserDetails} для интеграции с Spring Security.
 */
@Schema(description = "Сущность пользователя системы")
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@Data
public class User implements UserDetails {

    /**
     * Уникальный идентификатор пользователя.
     * <p>
     * Автоматически генерируется при сохранении в базе данных.
     */
    @Schema(
            description = "Уникальный идентификатор пользователя",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Логин пользователя (email).
     * <p>
     * Должен быть уникальным и не пустым.
     */
    @Schema(
            description = "Логин пользователя (email)",
            example = "user@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 32
    )
    @Column(nullable = false, unique = true, length = 32)
    private String username;

    /**
     * Пароль пользователя.
     * <p>
     * Хранится в зашифрованном виде. Доступен только для записи.
     */
    @Schema(
            description = "Пароль пользователя",
            example = "1234567890",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 128,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @Column(nullable = false, length = 128)
    private String password;

    /**
     * Имя пользователя.
     */
    @Schema(
            description = "Имя пользователя",
            example = "Иван",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 16
    )
    @Column(name = "first_name", nullable = false, length = 16)
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    @Schema(
            description = "Фамилия пользователя",
            example = "Иванов",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 16
    )
    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    /**
     * Телефон пользователя в формате +7 XXX XXX-XX-XX.
     */
    @Schema(
            description = "Телефон пользователя в формате +7 XXX XXX-XX-XX",
            example = "+7 999 99-99-99",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Column(nullable = false)
    private String phone;

    /**
     * Роль пользователя в системе.
     * <p>
     * Используется для определения прав доступа.
     */
    @Schema(
            description = "Роль пользователя в системе",
            example = "USER",
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = Role.class
    )
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Путь к аватару пользователя.
     * <p>
     * Может использоваться для формирования ссылки на изображение.
     */
    @Schema(
            description = "Путь к аватару пользователя",
            example = "/users/images/1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "image_path")
    private String imagePath;

    /**
     * Список объявлений пользователя.
     * <p>
     * Связь один-ко-многим с {@link Ad}.
     */
    @Schema(
            description = "Объявления пользователя",
            implementation = Ad.class,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Ad> ads;

    /**
     * Список комментариев пользователя.
     * <p>
     * Связь один-ко-многим с {@link Comment}.
     */
    @Schema(
            description = "Комментарии пользователя",
            implementation = Comment.class,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;

    /**
     * Возвращает коллекцию прав пользователя для Spring Security.
     *
     * @return список {@link GrantedAuthority} с ролью пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    /**
     * Проверяет, не истёк ли срок действия аккаунта.
     *
     * @return true всегда, аккаунт не истёк
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, не заблокирован ли аккаунт.
     *
     * @return true всегда, аккаунт не заблокирован
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, не истёк ли срок действия учетных данных.
     *
     * @return true всегда, учетные данные действительны
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, активен ли аккаунт.
     *
     * @return true всегда, аккаунт активен
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
