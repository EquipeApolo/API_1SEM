package br.gov.sp.fatec.project.controller;

import br.gov.sp.fatec.project.domain.Date;
import br.gov.sp.fatec.project.domain.Deliver;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.project.view.ProjectView;
import br.gov.sp.fatec.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("dev/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @JsonView(ProjectView.Project.class)
    public Project create(@RequestBody Project project) {
        return service.save(project);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView(ProjectView.Project.class)
    public List<Project> findAll(@PathVariable("id") Long id) {
//        List<Project> projects = service.findAll(id);
        return service.findAll(id);
    }

//    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
//    @JsonView(ProjectView.Project.class)
//    public Project findById(@PathVariable("id") Long id) {
//        return service.findById(id);
//    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PostMapping(value = "add-student/{projectId}/{studentId}")
    @JsonView(ProjectView.Project.class)
    public Project addStudent(@PathVariable("projectId") Long projectId, @PathVariable("studentId") Long studentId) {
        return service.addStudent(projectId, studentId);
    }

    @PostMapping(value = "/update")
    @JsonView(ProjectView.Project.class)
    public Project update(@RequestBody Project project) {
        return service.update(project);
    }

    @PutMapping(value = "/approve/{id}")
    @JsonView(ProjectView.Project.class)
    public Project approve(@PathVariable("id") Long id) {
        return service.approve(id);
    }

    @GetMapping(value = "/list-by-teacher/{teacherId}")
    @JsonView(ProjectView.Project.class)
    public List<Project> listProjectByTeacher(@PathVariable("teacherId") Long teacherId) {
        return service.getProjectByTeacher(teacherId);
    }

    @PostMapping(value = "/set-student-responsible/{projectId}/{studentId}/{teacherId}")
    @JsonView(ProjectView.Project.class)
    public Project setResponsibleStudent(@PathVariable("projectId") Long projectId,
                                         @PathVariable("teacherId") Long teacherId,
                                         @PathVariable("studentId") Long studentId) {

        return service.setStudentResponsible(studentId, projectId, teacherId);
    }

    // todo - se ja houverem alunos, sobrescrever ou so adicionar mais? depende de como vai funcionar o front. está sobrescrevendo
    @PostMapping(value = "/set-students/{projectId}/{teacherId}")
    @JsonView(ProjectView.Project.class)
    public Project setStudents(@PathVariable("projectId") Long projectId,
                               @PathVariable("teacherId") Long teacherId,
                               @RequestBody List<Student> studentList) {

        return service.setStudents(projectId, studentList, teacherId);
    }

    @DeleteMapping(value = "/remove-student/{projectId}/{studentId}/{teacherId}")
    @JsonView(ProjectView.Project.class)
    public Project removeStudent(@PathVariable("projectId") Long projectId,
                                 @PathVariable("studentId") Long studentId,
                                 @PathVariable("teacherId") Long teacherId) {
        return service.removeStudent(projectId, studentId);

    }

    @GetMapping(value = "/get-projects/{id}")
    @JsonView(ProjectView.Project.class)
    public Map<String, List<Project>> findProjectByStudent(@PathVariable("id") Long id) {
        return service.findProjectByStudent(id);
    }

    @PostMapping(value = "/deliver/{projectId}")
    @JsonView(ProjectView.Project.class)
    public Project deliverSolution(@PathVariable("projectId") Long projectId,
                                   @RequestBody Deliver deliver) {
        return service.setSolution(deliver, projectId);
    }

    @GetMapping(value = "/list-by-entrepreneur/{entrepreneurId}")
    @JsonView(ProjectView.Project.class)
    public List<Project> getProjectByEntrepreneur(@PathVariable("id") Long id) {
        return service.getProjectByEntrepreneur(id);
    }

    @PostMapping(value = "/set-chosen-date/{projectId}/{dateId}")
    @JsonView(ProjectView.Project.class)
    public Project setMeetingChosenDate(@PathVariable("dateId") Long dateId,
                                        @PathVariable("projectId") Long projectId) {
        return service.setMeetingChosenDate(dateId, projectId);
    }

    @PostMapping(value = "/set-possible-date/{projectId}")
    public Project setPossibleDate(@PathVariable("projectId") Long projectId,
                                   @RequestBody List<Date> possibleDate) {
        return service.setMeetingPossibleDate(possibleDate, projectId);
    }

    @PutMapping(value = "/set-teacher/{projectId}/{teacherId}")
    @JsonView(ProjectView.Project.class)
    public Project setTeacher(@PathVariable("teacherId") Long teacherId,
                              @PathVariable("projectId") Long projectId) {
        return service.setTeacher(teacherId, projectId);
    }
}
