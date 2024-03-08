package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Repository.TaskRepository;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
}
