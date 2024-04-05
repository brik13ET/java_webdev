package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Dto.ProjectDto;
import univer.webdev.gettingstarted.Model.Project;
import univer.webdev.gettingstarted.Repository.ProjectRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
	private final ProjectRepository projectRepository;

	public Optional<ProjectDto> create(
			String name,
			String description,
			LocalDate begin,
			LocalDate end
	) {

		var dbo = projectRepository.save(
				Project.builder()
						.name(name)
						.description(description)
						.begin(begin)
						.end(end)
						.build()
				);
		return Optional.of(
				new ProjectDto(
						dbo.getId(),
						dbo.getName(),
						dbo.getDescription(),
						dbo.getBegin(),
						dbo.getEnd()
				)
		);
	}

	public Optional<ProjectDto> getById(Long id) {
		var dbo = projectRepository.findById(id);
		if (dbo.isEmpty())
			return Optional.empty();
		return Optional.of(new ProjectDto(
				dbo.get().getId(),
				dbo.get().getName(),
				dbo.get().getDescription(),
				dbo.get().getBegin(),
				dbo.get().getEnd()
		));
	}

	public void delete(Long id) {
		projectRepository.deleteById(id);
	}

	public Set<ProjectDto> getByRange(LocalDate startDate, LocalDate endDate) {
		return projectRepository.findByBeginGreaterThanEqualAndEndLessThanEqual(startDate, endDate)
				.stream().map(
						(Project prj) ->
						{
							return new ProjectDto(
											prj.getId(),
											prj.getName(),
											prj.getDescription(),
											prj.getBegin(),
											prj.getEnd()
									);
						}
				).collect(Collectors.toSet());

	}

    public List<ProjectDto> search(String query)
	{
		return projectRepository.searchLike(query)
				.stream().map(ProjectDto::toDto).toList();
    }

	public List<ProjectDto> getAll() {
		return projectRepository.findAll()
				.stream().map(ProjectDto::toDto).toList();
	}

	public Dictionary<Long, Integer> pending() {
		var ret = new HashMap<Long, Integer>();
		var dbo = projectRepository.getPending();
		for (int i = 0; i < dbo.size(); i++) {
		}
			var curr = dbo.get(i);
			ret.put(curr.getProjectId(), curr.getTaskCount());
		}

	public Optional<ProjectDto> update(Long id, ProjectDto dto) {
		var dbo = projectRepository.findById(dto.getId());
		if (dbo.isPresent())
			return Optional.empty();
		if (dbo.isPresent())
		{
				var p = dbo.get();
				p.setName(       dto.getName()       != null ? dto.getName()        : dto.getName()         );
				p.setDescription(dto.getDescription()!= null ? dto.getDescription() : dto.getDescription()  );
				p.setBegin(      dto.getBegin()      != null ? dto.getBegin()       : dto.getBegin()        );
				p.setEnd(        dto.getEnd()        != null ? dto.getEnd()         : dto.getEnd()          );
				return Optional.of(ProjectDto.toDto(projectRepository.save(dbo.get())));
		}
		return Optional.empty();
	}
}