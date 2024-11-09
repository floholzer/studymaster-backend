package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table("tasks")
public class Task {

    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    private String title;
    private String description;

    @Column("due_date")
    private LocalDate dueDate;

    private String status = "open"; // Default value for status
    private String priority;

    private BigDecimal ects = BigDecimal.ZERO; // Default value for ects

    @Column("points_per_submission")
    private int pointsPerSubmission = 0;

    @Column("total_submissions")
    private int totalSubmissions = 0;

    @Column("completed_submissions")
    private int completedSubmissions = 0;

    @Column("created_at")
    private LocalDateTime createdAt; // Automatically set by the database

    private int pointsEarned;

}
