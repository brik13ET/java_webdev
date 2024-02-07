package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Repository.ProjectRepository;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
}
