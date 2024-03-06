package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
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
    private LocalDate begin;

    @NonNull
    private LocalDate end;

}
