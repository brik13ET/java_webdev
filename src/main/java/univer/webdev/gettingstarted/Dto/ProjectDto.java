package univer.webdev.gettingstarted.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate begin;
    private LocalDate end;

}
