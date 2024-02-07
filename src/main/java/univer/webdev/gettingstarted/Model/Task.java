package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("Task")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Task {

    @Id
    private Long id;

    @NonNull
    private String name;

    @Nullable
    private String description;

    @NonNull
    private Date begin;

    @NonNull
    private Date end;
}
