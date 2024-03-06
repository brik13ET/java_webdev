package univer.webdev.gettingstarted.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.Project;
import univer.webdev.gettingstarted.Model.Task;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public class TaskRepositoryImpl implements TaskRepository{

	private final JdbcTemplate template;
	@Autowired
	public TaskRepositoryImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	@Override
	public Optional<Task> add(String name, String description, LocalDate end, Boolean isFinished, Long prjId) {
		Long id = template.queryForObject("SELECT nextval('Task_ID')", Long.class);
		template.update("INSERT INTO Task VALUES (?,?,?,?,?)",id, name, description, end, isFinished, prjId);
		return Optional.of(
			new Task(id, name, description, end, isFinished, prjId)
		);
	}

	@Override
	public Set<Task> getByProject(Long prjId) {
		return null;
	}

	@Override
	public Set<Task> getByProject(Project prj) {
		return null;
	}

	@Override
	public void update(Task t) {

	}

	@Override
	public Long createOrUpdate(Task t) {
		return null;
	}

	@Override
	public void delete(Task t) {

	}
}
