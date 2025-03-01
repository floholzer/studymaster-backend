package at.technikum.studymasterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("badges")
public class Badge {

    @Id
    private Long id;
    private String name;
    private String description;
    private int points_required;
    private String badgeType;

}
