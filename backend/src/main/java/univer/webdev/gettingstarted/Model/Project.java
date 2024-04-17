package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id")
    @SequenceGenerator(name = "project_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @NonNull
    private String name;

    @Nullable
    @Column(name = "\"desc\"")
    private String description;

    @NonNull
    private LocalDate begin;

    @NonNull
    @Column(name = "\"end\"")
    private LocalDate end;

    @Builder.Default
    @OneToMany(
            mappedBy = "project",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Task> tasks = new ArrayList<Task>();

}