package univer.webdev.gettingstarted.Repository;

import jakarta.persistence.Tuple;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.Project;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	public Set<Project> findByBeginGreaterThanEqualAndEndLessThanEqual(LocalDate from, LocalDate to);

	@Query("select p from Project p where lower(p.name) like lower(concat('%', ?1,'%')) or lower(p.description) like lower(concat('%', ?1,'%'))")
	Set<Project> searchLike(String query);
	@Query("select p.id as id, (select count(t) from Task t where t.isFinished = False and p.id = t.project.id) as count from Project p")
	List<Map<String,Object>> getPending();

}