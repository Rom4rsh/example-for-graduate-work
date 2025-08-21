package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность объявления (рекламного объявления).
 * <p>
 * Содержит информацию о заголовке, описании, цене, изображении,
 * авторе и комментариях к объявлению.
 */
@Schema(description = "Сущность объявления (рекламного объявления)")
@Entity
@Table(name = "ads")
@NoArgsConstructor
@Getter
@Setter
public class Ad {

    /**
     * Уникальный идентификатор объявления.
     * <p>
     * Автоматически генерируется при сохранении в базе данных.
     */
    @Schema(
            description = "Уникальный идентификатор объявления",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Заголовок объявления.
     * <p>
     * Краткое название товара или услуги, максимум 32 символа.
     */
    @Schema(
            description = "Заголовок объявления",
            example = "Продам велосипед",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 32
    )
    @Column(nullable = false, length = 32)
    private String title;

    /**
     * Описание товара или услуги.
     * <p>
     * Подробная информация о предмете объявления, максимум 64 символа.
     */
    @Schema(
            description = "Описание товара/услуги",
            example = "Отличный горный велосипед, 2022 года выпуска",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 64
    )
    @Column(nullable = false, length = 64)
    private String description;

    /**
     * Цена товара или услуги в копейках/центах.
     * <p>
     * Значение должно быть неотрицательным.
     */
    @Schema(
            description = "Цена товара/услуги в копейках/центах",
            example = "1500000",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0"
    )
    @Column(nullable = false)
    private Integer price;

    /**
     * Путь к изображению товара.
     * <p>
     * Может использоваться для формирования ссылки на изображение в приложении.
     */
    @Schema(
            description = "Путь к изображению товара",
            example = "/ads/images/1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "image_path")
    private String imagePath;

    /**
     * Автор объявления.
     * <p>
     * Ссылка на объект {@link User}, который создал объявление.
     */
    @Schema(
            description = "Автор объявления (пользователь)",
            implementation = User.class,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * Список комментариев к объявлению.
     * <p>
     * Содержит все {@link Comment} объекты, связанные с данным объявлением.
     */
    @Schema(
            description = "Список комментариев к объявлению",
            implementation = Comment.class,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
