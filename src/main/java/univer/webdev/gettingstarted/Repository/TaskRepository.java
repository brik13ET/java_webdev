package univer.webdev.gettingstarted.Repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

	Optional<Task> add(
			String name,
			String description,
			Date end,
			Boolean isFinished,
			Long prjId
	);

	Set<Task> getByProject(Long prjId);
	Set<Task> getByProject(Project prj);

	void update(Task t);

	Long createOrUpdate(Task t);

	void delete(Task t);

}
