package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("subjects")
public class Subject {
    @Id
    private Long id;

    @Column("semester_id")
    private Long semesterId;

    private String name;
    private BigDecimal ects; // ECTS for the subject
    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("user_id")  // New field to link to a user
    private Long userId;

}

