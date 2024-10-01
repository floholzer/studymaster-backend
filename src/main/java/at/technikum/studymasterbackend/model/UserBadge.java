package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@Table("user_badges")
public class UserBadge {

    @Id
    private Long id;
    private LocalDateTime awarded_at;

    @Column("user_id")
    private Long user_id;

    @Column("badge_id")
    private Long badge_id;
}