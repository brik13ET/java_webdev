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
	@Query("select p.id as id, count(t) as count from Project p left join Task t on t.project.id = p.id where t.isFinished = false group by p.id")
	List<Map<String,Object>> getPending();

}