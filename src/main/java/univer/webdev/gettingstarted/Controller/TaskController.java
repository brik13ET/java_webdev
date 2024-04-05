package univer.webdev.gettingstarted.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import univer.webdev.gettingstarted.Dto.TaskDto;
import univer.webdev.gettingstarted.Service.TaskService;

@Controller
@AllArgsConstructor
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Возвращает все задачи;
    @GetMapping()
    ResponseEntity getAll(
            @PathVariable(required = true, name = "projectId") Long project_id
    ) {
        var opt = taskService.getAll(project_id);
        return new ResponseEntity(opt, HttpStatus.OK);
    }

    // Возвращает задачу по id задачи и id проекта;
    @GetMapping("/{taskId}")
    ResponseEntity getId(
            @PathVariable(required = true, name = "projectId")  Long project_id,
            @PathVariable(required = true, name = "taskId") Long task_id
    ) {
        var opt = taskService.getId(project_id, task_id);
        if (opt.isPresent())
            return new ResponseEntity(opt, HttpStatus.OK);
        return  new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Создаёт задачу. Задача передаётся в теле запроса. Пример JSON тела запроса:
    /*
        {
              "name": "task",
              "description": "desc",
              "completed": false,
              "endDate": "2020-02-02"
        }
    */
    @PostMapping()
    ResponseEntity create(
            @PathVariable(required = true, name = "projectId") Long project_id,
            @RequestBody TaskDto task
    ) {
        var dto = taskService.create(project_id, task);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    // Обновляет задачу. Задача передаётся в теле запроса. Пример JSON тела запроса:
    /*
        {
              "name": "task",
              "description": "desc",
              "completed": false,
              "endDate": "2020-02-02"
        }
    */
    @PutMapping("/{taskId}")
    ResponseEntity update(
            @PathVariable(required = true, name = "projectId") Long project_id,
            @PathVariable(required = true, name = "taskId") Long task_id,
            @RequestBody TaskDto task
    ) {
        taskService.update(task_id, project_id, task);
        return new ResponseEntity(HttpStatus.OK);
    }
    // Удаляет задачу.
    @DeleteMapping("/{taskId}")
    ResponseEntity deleteById(
            @PathVariable(required = true, name = "projectId") Long project_id,
            @PathVariable(required = true, name = "taskId") Long task_id
    ) {
        taskService.deleteById(project_id, task_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Удалить все завершённые задачи определённого проекта.
    @DeleteMapping("/finished")
    ResponseEntity deleteFinished(
            @PathVariable(required = true, name = "projectId") Long project_id
    ) {
        taskService.deleteFinished(project_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}