package univer.webdev.gettingstarted.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import univer.webdev.gettingstarted.Model.Project;

import java.time.LocalDate;



@Data
@AllArgsConstructor
public class ProjectDto {
	private Long id;
	private String name;
	private String description;
	private LocalDate begin;
	private LocalDate end;

	public static Project toEntity(ProjectDto dto) {
		return Project.builder()
				.id(dto.id)
				.name(dto.name)
				.description(dto.description)
				.begin(dto.begin)
				.build();
	}

	public static ProjectDto toDto(Project dbo) {
		return new ProjectDto(
				dbo.getId(),
				dbo.getName(),
				dbo.getDescription(),
				dbo.getBegin(),
				dbo.getEnd()
		);
	}
}