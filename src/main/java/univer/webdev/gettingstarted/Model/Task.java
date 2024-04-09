package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id")
    @SequenceGenerator(name = "task_id", initialValue = 1, allocationSize = 1)
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false, referencedColumnName = "id")
    private Project project;

}