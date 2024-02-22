package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Model.Project;
import univer.webdev.gettingstarted.Repository.ProjectRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    //    Создание проекта.
    //    POST /projects
    //    Должен вернуть 201 код в случае успешного создания проекта, а также сущность созданного проекта.
    public Optional<Project> create(
            String name,
            String description,
            LocalDate begin,
            LocalDate end
    ) {
        return projectRepository.create( name,  description,  begin,  end);
    }

    //    Модификация проекта
    //    PUT /projects/{projectId}
    //    Вернуть 200 код в случае успешной модификации проекта. Если проект с переданным ID не найден, то вернуть 404 код ответа.
    public boolean setName(Long id, String name) {
        return projectRepository.setName( id,  name);
    }

    public boolean setDescription(Long id, String description) {
        return projectRepository.setDescription( id,  description);
    }

    public boolean setBegin(Long id, LocalDate begin) {
        return projectRepository.setBegin( id,  begin);
    }

    public boolean setEnd(Long id, LocalDate end) {
        return projectRepository.setEnd( id,  end);
    }

    //    Удаление проекта
    //    DELETE /projects/{projectId}
    //    Вернуть 204 код.
    public boolean delete(Long id) {
        return projectRepository.delete( id);
    }

    //    Получение проекта
    //    GET /projects/{projectId}
    //    Вернуть 404 код ошибки если не найден.
    public Optional<Project> getById(Long id) {
        return projectRepository.getById(id);
    }

    //    Получение проектов с фильтрацией по диапазону. Дата начала и дата окончания должна быть в переданном интервале
    //    GET /projects?start_date={start_date}&end_date={end_date}
    public Set<Project> getByRange(LocalDate begin, LocalDate end) {
        return projectRepository.getByRange(begin, end);
    }
}
