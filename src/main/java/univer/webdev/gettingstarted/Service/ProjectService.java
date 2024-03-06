package univer.webdev.gettingstarted.Service;

import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import univer.webdev.gettingstarted.Dto.ProjectDto;
import univer.webdev.gettingstarted.Model.Project;
import univer.webdev.gettingstarted.Repository.ProjectRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    //    Создание проекта.
    //    POST /projects
    //    Должен вернуть 201 код в случае успешного создания проекта, а также сущность созданного проекта.
    public Optional<ProjectDto> create(
            String name,
            String description,
            LocalDate begin,
            LocalDate end
    ) {
        var dbo = projectRepository.create( name,  description,  begin,  end);
        if (dbo.isPresent())
            return Optional.of(
                    new ProjectDto(
                            dbo.get().getId(),
                            dbo.get().getName(),
                            dbo.get().getDescription(),
                            dbo.get().getBegin(),
                            dbo.get().getEnd()
                    )
            );
        return Optional.empty();
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
    public Optional<ProjectDto> getById(Long id) {
        var dbo = projectRepository.getById(id);
        if (dbo.isPresent()) {
            var mod = dbo.get();
            var pojo = new ProjectDto(mod.getId(), mod.getName(), mod.getDescription(), mod.getBegin(), mod.getEnd());
            return Optional.of(pojo);
        }
        return Optional.empty();
    }

    //    Получение проектов с фильтрацией по диапазону. Дата начала и дата окончания должна быть в переданном интервале
    //    GET /projects?start_date={start_date}&end_date={end_date}
    public Set<ProjectDto> getByRange(LocalDate begin, LocalDate end) {
        return projectRepository.getByRange(
                begin, end
        ).stream().map(
                        (Project p) -> {
                            return new ProjectDto(
                                    p.getId(),
                                    p.getName(),
                                    p.getDescription(),
                                    p.getBegin(),
                                    p.getEnd());
                        }
                        ).collect(Collectors.toSet());
    }
}
