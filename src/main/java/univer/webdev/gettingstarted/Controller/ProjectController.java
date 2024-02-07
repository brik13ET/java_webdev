package univer.webdev.gettingstarted.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import univer.webdev.gettingstarted.Model.Project;

import java.util.Date;


//    Создание проекта.
//    POST /projects
//    Должен вернуть 201 код в случае успешного создания проекта, а также сущность созданного проекта.
//    Модификация проекта
//    PUT /projects/{projectId}
//    Вернуть 200 код в случае успешной модификации проекта. Если проект с переданным ID не найден, то вернуть 404 код ответа.
//    Удаление проекта
//    DELETE /projects/{projectId}
//    Вернуть 204 код.
//    Получение проекта
//    GET /projects/{projectId}
//    Вернуть 404 код ошибки если не найден.
//    Получение проектов с фильтрацией по диапазону. Дата начала и дата окончания должна быть в переданном интервале
//    GET /projects?start_date={start_date}&end_date={end_date}

@Controller
public class ProjectController {
    // Создание проекта
    @PostMapping("/projects")
    ResponseEntity createProject() {
        return new ResponseEntity<>("createProject", HttpStatus.NOT_IMPLEMENTED);
    }

    // Модификация проекта
    @PutMapping("/projects/{projectId}")
    ResponseEntity updateProject(@RequestParam Integer projectId, @RequestBody Project prj) {
        return new ResponseEntity<>("updateProject", HttpStatus.NOT_IMPLEMENTED);
    }

    // Удаление проекта
    @DeleteMapping("/projects/{projectId}")
    ResponseEntity deleteProject(@RequestParam Integer projectId) {
        return new ResponseEntity<>("deleteProject", HttpStatus.NOT_IMPLEMENTED);
    }

    // Получение проекта
    @GetMapping("/projects/{projectId}")
    ResponseEntity getProject(@RequestParam Integer projectId) {
        return new ResponseEntity<>("getProject", HttpStatus.NOT_IMPLEMENTED);
    }

    // Получение проектов
    @GetMapping("/projects?start_date={start_date}&end_date={end_date}")
    ResponseEntity getProjectFiltered(@RequestParam Date start_date, @RequestParam Date end_date) {
        return new ResponseEntity<>("getProjectFiltered", HttpStatus.NOT_IMPLEMENTED);
    }
}
