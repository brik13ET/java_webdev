package univer.webdev.gettingstarted.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
	@Query(value = "select t from Task t where t.project.id = ?1 ", nativeQuery = false)
	List<Task> findAllByProject(Long prjId);

	@Modifying
	@Transactional
	@Query(value = "delete from Task t where t.project.id = ?1 and t.isFinished = true ", nativeQuery = false)
	void deleteFinishedByProject(Long prjId);
}