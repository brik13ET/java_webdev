package univer.webdev.gettingstarted.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
	private final JdbcTemplate template;

	@Autowired
	public ProjectRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Optional<Project> create(String name, String description, LocalDate begin, LocalDate end) {
		Long id = template.queryForObject("SELECT nextval('project_ID')", Long.class);
		template.update("INSERT INTO Project VALUES (?,?,?,?,?)",
				id,
				name.substring(0, Math.min(name.length(), 255)),
				description.substring(0, Math.min(description.length(), 255)),
				begin,
				end
		);
		return Optional.of(new Project(id, name, description, begin, end));
	}


	@Override
	public boolean setName(Long id, String name) {
		return template.update("UPDATE Project SET name = ? WHERE id = ?", name, id) == 1;
	}

	@Override
	public boolean setDescription(Long id, String description) {
		return template.update("UPDATE Project SET \"desc\" = ? WHERE id = ?", description, id) == 1;
	}

	@Override
	public boolean setBegin(Long id, LocalDate begin) {
		return template.update("UPDATE Project SET \"begin\" = ? WHERE id = ?", begin, id) == 1;
	}

	@Override
	public boolean setEnd(Long id, LocalDate end) {
		return template.update("UPDATE Project SET \"end\" = ? WHERE id = ?", end, id) == 1;
	}

	@Override
	public boolean delete(Long id) {
		return template.update("DELETE FROM Project WHERE id = ?", id) == 1;
	}

	@Override
	public Optional<Project> getById(Long id) {
		List<Project> res = template.query(
				"SELECT * FROM Project WHERE id = ?",
				new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Project(
								rs.getLong("id"),
								rs.getString("name"),
								rs.getString("desc"),
								rs.getDate("begin").toLocalDate(),
								rs.getDate("end").toLocalDate()
						);
					}
				},
				id
		);
		if (res.isEmpty())
			return Optional.empty();
		return Optional.of(res.getFirst());
	}

	@Override
	public Set<Project> getByRange(LocalDate begin, LocalDate end) {
		return new HashSet(
				template.query(
						"SELECT * FROM Project WHERE begin >= ? AND \"end\" <= ?",
						new RowMapper<Project>() {
							@Override
							public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
								return new Project(
										rs.getLong("id"),
										rs.getString("name"),
										rs.getString("desc"),
										rs.getDate("begin").toLocalDate(),
										rs.getDate("end").toLocalDate()
								);
							}
						},
						begin,
						end
				)
		);
	}
}
