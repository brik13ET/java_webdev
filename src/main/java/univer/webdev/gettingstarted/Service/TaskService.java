package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Dto.TaskDto;
import univer.webdev.gettingstarted.Model.Task;
import univer.webdev.gettingstarted.Repository.ProjectRepository;
import univer.webdev.gettingstarted.Repository.TaskRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;

	public List<TaskDto> getAll(Long project_id) {
		return taskRepository.findAllByProject(project_id)
				.stream()
				.map(
						t -> new TaskDto(
								t.getId(),
								t.getName(),
								t.getDescription(),
								t.getEnd(),
								t.getIsFinished()
						)
				).toList();
	}

	public Optional<TaskDto> getId(Long project_id, Long task_id) {
		return taskRepository.findById(task_id).map(
				t -> new TaskDto(
						t.getId(),
						t.getName(),
						t.getDescription(),
						t.getEnd(),
						t.getIsFinished()
				)
		);
	}

	public Optional<TaskDto> create(Long project_id, TaskDto taskDto) {
		Optional<TaskDto> ret = Optional.empty();
		var dbo = projectRepository.findById(project_id);
		if (dbo.isEmpty())
			return ret;

		var p = dbo.get();
		var baked = Task.builder()
				.name(taskDto.getName())
				.description(taskDto.getDescription())
				.end(taskDto.getEnd())
				.isFinished(taskDto.getIsFinished())
				.project(p)
				.build();
		taskRepository.saveAndFlush(baked);
		ret = Optional.of(
				new TaskDto(
						baked.getId(),
						baked.getName(),
						baked.getDescription(),
						baked.getEnd(),
						baked.getIsFinished()
				));
		return ret;
	}

	public Optional<TaskDto> update(Long task_id, Long project_id, TaskDto taskDto) {
		var dbo_task = taskRepository.findById(task_id);

		if (dbo_task.isPresent()) {
			var dbo_project = projectRepository.findById(project_id);
			if (dbo_project.isEmpty())
				return Optional.empty();
			var task = dbo_task.get();
			var project = dbo_project.get();
			task.setName 		(taskDto.getName()		  == null ? task.getName()		: taskDto.getName()		  );
			task.setDescription (taskDto.getDescription() == null ? task.getDescription()	: taskDto.getDescription());
			task.setEnd 		(taskDto.getEnd()		  == null ? task.getEnd()			: taskDto.getEnd()		  );
			task.setIsFinished 	(taskDto.getIsFinished()  == null ? task.getIsFinished()	: taskDto.getIsFinished() );
			task.setProject 	(project);
			taskRepository.saveAndFlush(task);
		}
		return Optional.empty();
	}

	public void deleteById(Long project_id, Long task_id) {
		var t = taskRepository.findById(task_id);
		if (t.isPresent() && t.get().getProject().getId() == project_id)
			taskRepository.deleteById(task_id);
	}

	public void deleteFinished(Long project_id) {
		taskRepository.deleteFinishedByProject(project_id);
	}

}