package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id")
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

}
