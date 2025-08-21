package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность комментария к объявлению.
 * <p>
 * Содержит текст комментария, дату создания, ссылку на объявление и автора.
 */
@Schema(description = "Сущность комментария к объявлению")
@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    /**
     * Уникальный идентификатор комментария.
     * <p>
     * Автоматически генерируется при сохранении в базе данных.
     */
    @Schema(
            description = "Уникальный идентификатор комментария",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Текст комментария.
     * <p>
     * Максимальная длина — 64 символа.
     */
    @Schema(
            description = "Текст комментария",
            example = "Этот товар в отличном состоянии!",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 64
    )
    @Column(nullable = false, length = 64)
    private String text;

    /**
     * Дата и время создания комментария в миллисекундах с эпохи Unix.
     */
    @Schema(
            description = "Дата и время создания комментария в миллисекундах с эпохи Unix",
            example = "1678901234567",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    /**
     * Объявление, к которому относится комментарий.
     * <p>
     * Ссылка на объект {@link Ad}.
     */
    @Schema(
            description = "Объявление, к которому относится комментарий",
            implementation = Ad.class,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    /**
     * Автор комментария.
     * <p>
     * Ссылка на объект {@link User}, который создал комментарий.
     */
    @Schema(
            description = "Автор комментария",
            implementation = User.class,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
}
