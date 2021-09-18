package br.gov.sp.fatec.project.controller;

import br.gov.sp.fatec.project.domain.Deliver;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.teacher.domain.Teacher;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static br.gov.sp.fatec.project.fixture.ProjectFixture.newProject;
import static br.gov.sp.fatec.student.fixture.StudentFixture.newStudent;
import static br.gov.sp.fatec.teacher.fixture.TeacherFixture.newTeacher;
import static br.gov.sp.fatec.utils.commons.JSONParser.toJSON;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    private static final String URL = "/dev/project";

    private MockMvc mockMvc;

    @InjectMocks
    private ProjectController controller;

    @Mock
    private ProjectService service;

     @BeforeEach
    public void onInit() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

//    @Test
//    public void findAll_shouldSucceed() throws Exception {
//        List<Project> projectList = Lists.newArrayList(
//                newProject(1L),
//                newProject(2L),
//                newProject(3L));
//
//        when(service.findAll()).thenReturn(projectList);
//
//        mockMvc.perform(get(URL))
//                .andExpect(status().isOk());
//
//        verify(service).findAll();
//    }

//    @Test
//    public void findById_shouldSucceed() throws Exception {
//        Project project = newProject();
//        when(service.findById(project.getId())).thenReturn(project);
//
//        mockMvc.perform(get(URL + "/" + project.getId()))
//                .andExpect(status().isOk());
//    }

    @Test
    public void delete_shouldSucceed() throws Exception {
        Project project = newProject();

        mockMvc.perform(delete(URL + "/" + project.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudent_shouldSucceed() throws Exception {
        Project project = newProject();

        when(service.addStudent(project.getId(), 1L)).thenReturn(project);
        mockMvc.perform(post(URL + "/add-student/" + project.getId() + "/" + 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void approve_shouldSucceed() throws Exception {
        Project project = newProject();
        when(service.approve(project.getId())).thenReturn(project);

        mockMvc.perform(put(URL + "/approve/" + project.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void setResponsibleStudent_shouldSucceed() throws Exception {
        Project project = newProject();
        Student student = newStudent(1L, true);
        Teacher teacher = newTeacher();

        project.setStudentResponsible(student);

        when(service.setStudentResponsible(student.getId(), project.getId(), teacher.getId())).thenReturn(project);
        mockMvc.perform(post(URL + "/set-student-responsible/" + project.getId() + "/" + student.getId() + "/" + teacher.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void listProjectByTeacher_shouldSucceed() throws Exception {
        Teacher teacher = newTeacher();
        List<Project> projectList = Lists.newArrayList(
                newProject(1L),
                newProject(2L),
                newProject(3L));

        for(Project project : projectList) {
            project.setTeacher(teacher);
        }

        when(service.getProjectByTeacher(teacher.getId())).thenReturn(projectList);
        mockMvc.perform(get(URL + "/list-by-teacher/" + teacher.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void removeStudent_shouldSucceed() throws Exception {
        Project project = newProject();
        Student student = newStudent();
        Teacher teacher = project.getTeacher();

        when(service.removeStudent(project.getId(), student.getId())).thenReturn(project);

        mockMvc.perform(delete(URL + "/remove-student/" + project.getId() + "/" + student.getId() + "/" + teacher.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void setStudents_shouldSucceed() throws Exception {
        Project project = newProject();
        Teacher teacher = newTeacher();

        List<Student> studentList = Lists.newArrayList(newStudent(1L, true),
                newStudent(2L, true),
                newStudent(3L, true));

        project.setStudents(studentList);

        mockMvc.perform(post(URL + "/set-students/" + project.getId() + "/" +  teacher.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(toJSON(studentList))))
                .andExpect(status().isOk());
    }


    @Test
    public void listProjectByStudent_shouldSucceed() throws Exception {
        List<Student> studentList =  Lists.newArrayList(newStudent());
        List<Project> projectList = Lists.newArrayList(
                newProject(1L),
                newProject(2L),
                newProject(3L));

        Map<String, List<Project>> projects = new HashMap<>();
        projects.put("responsible", null);
        projects.put("team", projectList);

        when(service.findProjectByStudent(studentList.get(0).getId())).thenReturn(projects);
        mockMvc.perform(get(URL + "/get-projects/" + studentList.get(0).getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deliverSolution_shouldSucceed() throws Exception {
        Project project = newProject();
        Deliver deliver = project.getDeliver().get(0);

        when(service.setSolution(deliver, project.getId())).thenReturn(project);
        mockMvc.perform(post(URL + "/deliver/" + project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(toJSON(deliver))))
                .andExpect(status().isOk());
    }
}
