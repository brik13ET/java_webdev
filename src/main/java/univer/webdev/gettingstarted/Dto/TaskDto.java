package univer.webdev.gettingstarted.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import univer.webdev.gettingstarted.Model.Task;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate end;
    private Boolean isFinished;

    public static Task toEntity(TaskDto dto)
    {
        return Task.builder()
                .id(dto.id)
                .name(dto.name)
                .description(dto.description)
                .end(dto.end)
                .isFinished(dto.isFinished)
                .build();
    }

    public static TaskDto toDto(Task dbo)
    {
        return new TaskDto(
                dbo.getId(),
                dbo.getName(),
                dbo.getDescription(),
                dbo.getEnd(),
                dbo.getIsFinished()
        );
    }
}