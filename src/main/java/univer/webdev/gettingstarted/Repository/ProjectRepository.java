package univer.webdev.gettingstarted.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.Project;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	public Set<Project> findByBeginGreaterThanEqualAndEndLessThanEqual(LocalDate from, LocalDate to);
}