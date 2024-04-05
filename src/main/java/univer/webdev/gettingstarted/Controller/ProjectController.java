package univer.webdev.gettingstarted.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import univer.webdev.gettingstarted.Dto.ProjectDto;
import univer.webdev.gettingstarted.Service.ProjectService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // search
    @GetMapping(value = "", params = "search")
    ResponseEntity search(String query)
    {
        return new ResponseEntity(projectService.search(query),HttpStatus.OK);
    }

    // Получение проекта
    @GetMapping("/{projectId}")
    ResponseEntity getProject(
            @PathVariable(required = true, name = "projectId") Long id
    ) {
        var dbo = projectService.getById(id);
        if (dbo.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dbo.get(), HttpStatus.OK);

    }

    // Создание проекта
    @PostMapping
    ResponseEntity createProject(
            @RequestBody ProjectDto rb
    ) {

        if (!rb.getBegin().isBefore(rb.getEnd())) return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);

        var res = this.projectService.create(
                rb.getName(),
                rb.getDescription(),
                rb.getBegin(),
                rb.getEnd()
        );
        if (res.isEmpty()) return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(res.get(), HttpStatus.valueOf(201));
    }

    // Модификация проекта
    @PutMapping("/{projectId}")
    ResponseEntity updateProject(
            @PathVariable(required = true, name = "projectId") Long id,
            @RequestBody ProjectDto rb
    ) {
        rb.setId(id);
        var ret = projectService.update(id, rb);
        if (ret.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Удаление проекта
    @DeleteMapping("/{projectId}")
    ResponseEntity deleteProject(
            @PathVariable(required = true, name = "projectId") Long id
    ) {
        projectService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pending_count")
    ResponseEntity getPending()
    {
        return new ResponseEntity(projectService.pending(), HttpStatus.OK);
    }


    // Получение проекта
    @GetMapping("/all")
    ResponseEntity getAll() {
        var dto = projectService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    // Получение проектов
    @GetMapping(params = { "start_date", "end_date" })
    ResponseEntity getProjectFiltered(@RequestParam(name = "start_date") LocalDate start_date, @RequestParam(name = "end_date") LocalDate end_date) {
        var dbo = projectService.getByRange(start_date, end_date);
        if (dbo.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dbo, HttpStatus.OK);
    }

}