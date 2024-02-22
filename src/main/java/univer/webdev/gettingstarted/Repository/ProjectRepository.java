package univer.webdev.gettingstarted.Repository;

import univer.webdev.gettingstarted.Model.Project;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface ProjectRepository {
//    Создание проекта
    public Optional<Project> create(
            String name,
            String description,
            LocalDate begin,
            LocalDate end
    );
//    Модификация проекта
        /**
     * Set Project name by ID
     * @param id Project ID
     * @param name New Project name
     * @return TRUE if succsess, FALSE otherwise
     */
    public boolean setName(Long id, String name);
        /**
     * Set Project description by ID
     * @param id Project ID
     * @param description New Project name
     * @return TRUE if succsess, FALSE otherwise
     */
    public boolean setDescription(Long id, String description);
        /**
     * Set Project begin date by ID
     * @param id Project ID
     * @param begin New Project begin date
     * @return TRUE if succsess, FALSE otherwise
     */
    public boolean setBegin(Long id, LocalDate begin);
        /**
     * Set Project end date by ID
     * @param id Project ID
     * @param end New Project end date
     * @return TRUE if succsess, FALSE otherwise
     */
    public boolean setEnd(Long id, LocalDate end);
//    Удаление проекта
        /**
     * Delete by ID
     * @param id Project ID
     * @return TRUE if succsess, FALSE otherwise
     */
    public boolean delete(Long id);
//    Получение проекта
    public Optional<Project> getById(Long id);
//    Получение проектов с фильтрацией по диапазону. Дата начала и дата окончания должна быть в переданном интервале
    public Set<Project> getByRange(LocalDate begin, LocalDate end);
}
