package br.gov.sp.fatec.student.controller;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.service.StudentService;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
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
import java.util.*;

import static br.gov.sp.fatec.student.fixture.StudentFixture.newStudent;
import static br.gov.sp.fatec.utils.commons.JSONParser.toJSON;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    private static final String URL = "/dev/student";

    @InjectMocks
    private  StudentController controller;

    @Mock
    private StudentService service;

    private MockMvc mockMvc;

     @BeforeEach
    public void onInit() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void findAll_shouldSucceed() throws Exception {
        List<Student> studentList = Lists.newArrayList(newStudent(1L, true),
                newStudent(2L, true),
                newStudent(3L, true));

        when(service.findAll()).thenReturn(studentList);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk());

        verify(service).findAll();
    }

    @Test
    public void findActive_shouldSucceed() throws Exception {
        List<Student> studentList = Lists.newArrayList(newStudent(1L, true),
                newStudent(2L, true),
                newStudent(3L, true),
                newStudent(4L, true),
                newStudent(5L, true));

        when(service.findActive()).thenReturn(studentList);

        mockMvc.perform(get(URL + "/active"))
                .andExpect(status().isOk());
    }

    @Test
    public void findById_shouldSucceed() throws Exception {
        Student student = newStudent();
        when(service.findById(student.getId())).thenReturn(student);

        mockMvc.perform(get(URL + "/" + student.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void update_ShouldSucceed() throws Exception {
        Student student = newStudent();
        Student updated = newStudent();
        updated.setEmail("newEmail@test.com");

        mockMvc.perform(put(URL + "/" + student.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(toJSON(student))))
                .andExpect(status().isOk());
    }

    @Test
    public void activate_shouldSucceed() throws Exception {
        Student student = newStudent();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", student.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());

        mockMvc.perform(get(URL + "/activate/" + b64))
                .andExpect(status().isOk());
    }
}
