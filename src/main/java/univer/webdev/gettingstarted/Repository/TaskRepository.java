package univer.webdev.gettingstarted.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univer.webdev.gettingstarted.Model.*;

import java.util.Date;
import java.util.Set;

@Repository
public interface TaskRepository  extends CrudRepository<Task, Integer> {

    void create(Task prj);

    Set<Task> getById(Long id);
    Set<Task> getByName(String name);
    Set<Task> getByBegin(Date begin);
    Set<Task> getByEnd(Date end);


    void setId(Task task, Long id);
    void setName(Task task, String name);
    void setBegin(Task task, Date begin);
    void setEnd(Task task, Date end);

    void deleteById();
    void deleteByName();
    void deleteByBeginDate(Date date);
    void deleteByEndDate(Date date);
    void deleteByBeginEndDate(Date date);

}
