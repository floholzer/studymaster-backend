package at.technikum.studymasterbackend.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User {

    @Id
    private Long id;

    private String username;
    private String email;
    private String password;
    private String first_name;
    private String last_name;

}