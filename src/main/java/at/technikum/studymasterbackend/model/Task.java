package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Data
@Table("tasks")
public class Task {

    @Id
    private Long id;

    @Column("user_id")  // Verweis auf den Benutzer in der Tabelle `tasks`
    private Long userId;

    private String title;
    private String description;
    private Date due_date;
    private String status;
    private String priority;


}
