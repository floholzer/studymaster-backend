package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("semesters")
public class Semester {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    private String name;
    private BigDecimal ects; // Total ECTS for the semester
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("status")
    private String status = "open"; // Default status is 'open'
}

