package univer.webdev.gettingstarted.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import univer.webdev.gettingstarted.Model.Project;
import univer.webdev.gettingstarted.Service.ProjectService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	// Создание проекта
	@PostMapping
	ResponseEntity createProject(
			@RequestBody Map<String,String> rb
	) {
		Optional<String>    name = Optional.ofNullable(rb.get("name"));
		Optional<String>    description = Optional.ofNullable(rb.get("description"));
		Optional<LocalDate> begin = Optional.empty();
		Optional<LocalDate> end = Optional.empty();
		var begin_rv = rb.get("begin");
		if (begin_rv != null)
			begin = Optional.of(LocalDate.parse(begin_rv));
		var end_rv = rb.get("end");
		if (end_rv != null)
			end = Optional.of(LocalDate.parse(end_rv));
		if (name.isEmpty())
			name = Optional.of("Без имени");
		if (description.isEmpty())
			description = Optional.of("");
		if (begin.isEmpty()) if (end.isEmpty()) {
			begin = Optional.of(LocalDate.now());
			end = Optional.of(begin.get().plusWeeks(1));
		} else begin = Optional.of(end.get().minusWeeks(1));
		else if (end.isEmpty()) end = Optional.of(begin.get().plusWeeks(1));

		if (!begin.get().isBefore(end.get())) return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);

		var res = this.projectService.create(
				name.get(),
				description.get(),
				begin.get(),
				end.get()
		);
		if (res.isEmpty()) return new ResponseEntity<>("createProject", HttpStatus.BAD_REQUEST);
		else return new ResponseEntity<>(res.get(), HttpStatus.valueOf(201));
	}

	// Модификация проекта
	@PutMapping("/{projectId}")
	ResponseEntity updateProject(
			@PathVariable(required = true, name = "projectId") Long id,
			@RequestBody Map<String,String> rb
	) {
		if (projectService.getById(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Optional<String>    name = Optional.ofNullable(rb.get("name"));
		Optional<String>    description = Optional.ofNullable(rb.get("description"));
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
		return  new ResponseEntity<>(dbo.get(),HttpStatus.OK);

	}

	// Получение проектов
	@GetMapping
	ResponseEntity getProjectFiltered(@RequestParam(name = "start_date") LocalDate start_date, @RequestParam(name = "end_date") LocalDate end_date) {
		var dbo = projectService.getByRange(start_date, end_date);
		if (dbo.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return  new ResponseEntity<>(dbo,HttpStatus.OK);
	}
}
