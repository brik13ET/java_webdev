package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("Project")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Project {

    @Id
    private Long id;

    @NonNull
    private String name;

    @Nullable
    private String description;

    @NonNull
    private Date end;

    @NonNull
    private Boolean isFinished;
}
