package univer.webdev.gettingstarted.Model;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Set;

@Data
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
    private Date end;

    @NonNull
    private Boolean isFinished;

    @NonNull
    private Long prjId;
}
