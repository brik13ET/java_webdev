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
            @RequestBody Map<String, String> rb
    ) {
        if (projectService.getById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Optional<String> name = Optional.ofNullable(rb.get("name"));
        Optional<String> description = Optional.ofNullable(rb.get("description"));
        Optional<LocalDate> begin = Optional.empty();
        Optional<LocalDate> end = Optional.empty();
        var begin_rv = rb.get("begin");
        if (begin_rv != null)
            begin = Optional.of(LocalDate.parse(begin_rv));
        var end_rv = rb.get("end");
        if (end_rv != null)
            end = Optional.of(LocalDate.parse(end_rv));
        if (name.isPresent())
            projectService.setName(id, name.get());
        if (description.isPresent())
            projectService.setDescription(id, description.get());
        if (begin.isPresent())
            projectService.setBegin(id, begin.get());
        if (end.isPresent())
            projectService.setEnd(id, end.get());
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

    // search
    @GetMapping(value = "", params = "search")
    ResponseEntity search(String query)
    {
        return new ResponseEntity(projectService.search(query),HttpStatus.OK);
    }

    @GetMapping("/pending_count")
    ResponseEntity getPending()
    {
        return new ResponseEntity(projectService.pending(), HttpStatus.OK);
    }

}