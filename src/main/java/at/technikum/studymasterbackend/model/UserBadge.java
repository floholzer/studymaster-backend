package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Data
@Table("user_badges")
public class UserBadge {

    @Id
    private Long id;
    private Date awarded_at;

    @Column("user_id")
    private Long userId;

    @Column("badge_id")
    private Long badgeId;
}