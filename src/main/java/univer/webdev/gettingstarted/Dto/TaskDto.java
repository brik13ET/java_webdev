package univer.webdev.gettingstarted.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate end;
    private Boolean isFinished;
    private Long prjId;
}
