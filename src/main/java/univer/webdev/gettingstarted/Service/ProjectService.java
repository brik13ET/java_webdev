package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Dto.ProjectDto;
import univer.webdev.gettingstarted.Model.Project;
import univer.webdev.gettingstarted.Repository.ProjectRepository;

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

	public void setName(Long id, String s) {
		projectRepository.findById(id).ifPresent(
				p ->{
					p.setName(s);
					projectRepository.saveAndFlush(p);
				}
		);
	}

	public void setDescription(Long id, String s) {
		projectRepository.findById(id).ifPresent(
				p ->{
					p.setDescription(s);
					projectRepository.saveAndFlush(p);
				}
		);
	}

	public void setBegin(Long id, LocalDate localDate) {
		projectRepository.findById(id).ifPresent(
				p ->{
					p.setBegin(localDate);
					projectRepository.saveAndFlush(p);
				}
		);
	}

	public void setEnd(Long id, LocalDate localDate) {
		projectRepository.findById(id).ifPresent(
				p ->{
					p.setEnd(localDate);
					projectRepository.saveAndFlush(p);
				}
		);
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
		return
	}
}