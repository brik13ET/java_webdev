package univer.webdev.gettingstarted.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

}