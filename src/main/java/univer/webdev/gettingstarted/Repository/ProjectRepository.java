package univer.webdev.gettingstarted.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.Project;

import java.util.Date;
import java.util.Set;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    void create(Project prj);

    Set<Project> getFinished();
    Set<Project> getUninished();
    Set<Project> getNamed(String name);
    Set<Project> getWithEnd(Date date);

    Project setName(String name);
    Project setDescription(String description);
    Project setEnd(Date end);
    Project setFinished(Boolean isFinished);

    void deleteById();
    void deleteByName();
    void deleteByFinished(Boolean finished);
    void deleteByDate(Date date);

}
