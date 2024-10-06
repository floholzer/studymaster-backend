package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("user_sessions")
public class UserSession {

    @Id
    private String session_id;

    private Long user_id;      // FK to users table
    private Date creation_time;
    private Date expiry_time;
}
