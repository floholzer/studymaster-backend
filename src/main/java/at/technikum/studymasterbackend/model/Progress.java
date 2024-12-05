package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("progress")
public class Progress {
    @Id
    private Long id;
    @Column("user_id")
    private Long userId;
    @Column("task_id")
    private Long task_id;
    private double progress_percentage;
    private int points_earned;
    private int level;
}
