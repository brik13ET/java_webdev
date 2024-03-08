package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id")
    private Long id;

    @NonNull
    private String name;

    @Nullable
    private String description;

    @NonNull
    @Column(name = "\"end\"")
    private LocalDate end;

    @NonNull
    private Boolean isFinished;

    @NonNull
    private Long prjId;
}
