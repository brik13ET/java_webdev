package univer.webdev.gettingstarted.Repository;

import univer.webdev.gettingstarted.Model.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

	Optional<Task> add(
			String name,
			String description,
			LocalDate end,
			Boolean isFinished,
			Long prjId
	);

	// TODO: implement
	Set<Task> getByProject(Long prjId);

	// TODO: implement
	Set<Task> getByProject(Project prj);

	// TODO: implement
	void update(Task t);

	// TODO: implement
	Long createOrUpdate(Task t);

	// TODO: implement
	void delete(Task t);

}
