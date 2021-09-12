package br.gov.sp.fatec.student.fixture;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.student.domain.Student;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static br.gov.sp.fatec.project.fixture.ProjectFixture.newProject;

public class StudentFixture {

    private static final String EMAIL = "email@teste.com";
    private static final String NAME = "test";
    private static final String PASSWORD = "test";
    private static final Long ID = 1L;
    private static final Boolean ACTIVE = true;

    public static Student newStudent(Long id, boolean active) {
        Student student = new Student();
        student.setId(id);
        student.setActive(active);
        student.setEmail(EMAIL);
        student.setPassword(PASSWORD);
        student.setProjects(getProjectList());

        return student;
    }

    public static Student newStudent() {
        Student student = new Student();
        student.setId(ID);
        student.setActive(ACTIVE);
        student.setEmail(EMAIL);
        student.setPassword(PASSWORD);
        student.setProjects(getProjectList());

        return student;
    }

    public static Student newStudentNoProject(Long id, boolean active) {
        Student student = new Student();
        student.setId(id);
        student.setActive(active);
        student.setEmail(EMAIL);
        student.setPassword(PASSWORD);

        return student;
    }

    public static Student newStudentNoProject() {
        Student student = new Student();
        student.setId(ID);
        student.setActive(ACTIVE);
        student.setEmail(EMAIL);
        student.setPassword(PASSWORD);

        return student;
    }

    private static List<Project> getProjectList() {
        List<Project> projects = new LinkedList<>();

        for (long i = 1L; i < 4L; i++) {
            projects.add(newProject(i));
        }

        return projects;
    }
}
