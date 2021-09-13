//package br.gov.sp.fatec.teacher.controller;
//
//import br.gov.sp.fatec.teacher.domain.Teacher;
//import br.gov.sp.fatec.teacher.service.TeacherService;
//import org.assertj.core.util.Lists;
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//import static br.gov.sp.fatec.teacher.fixture.TeacherFixture.newTeacher;
//import static br.gov.sp.fatec.utils.commons.JSONParser.toJSON;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class teacherControllerTest {
//
//    private static final String URL = "/dev/teacher";
//
//    @InjectMocks
//    private  TeacherController controller;
//
//    @Mock
//    private TeacherService service;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void onInit() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//                .build();
//    }
//
//    @Test
//    public void findAll_shouldSucceed() throws Exception {
//        List<Teacher> teacherList = Lists.newArrayList(newTeacher(1L, true),
//                newTeacher(2L, true),
//                newTeacher(3L, true));
//
//        when(service.findAll()).thenReturn(teacherList);
//
//        mockMvc.perform(get(URL))
//                .andExpect(status().isOk());
//
//        verify(service).findAll();
//    }
//
//    @Test
//    public void findActive_shouldSucceed() throws Exception {
//        List<Teacher> teacherList = Lists.newArrayList(newTeacher(1L, true),
//                newTeacher(2L, true),
//                newTeacher(3L, true),
//                newTeacher(4L, true),
//                newTeacher(5L, true));
//
//        when(service.findActive()).thenReturn(teacherList);
//
//        mockMvc.perform(get(URL + "/active"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void findById_shouldSucceed() throws Exception {
//        Teacher teacher = newTeacher();
//        when(service.findById(teacher.getId())).thenReturn(teacher);
//
//        mockMvc.perform(get(URL + "/" + teacher.getId()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void update_ShouldSucceed() throws Exception {
//        Teacher teacher = newTeacher();
//        Teacher updated = newTeacher();
//        updated.setEmail("newEmail@test.com");
//
//        mockMvc.perform(put(URL + "/" + teacher.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Objects.requireNonNull(toJSON(teacher))))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void activate_shouldSucceed() throws Exception {
//        Teacher teacher = newTeacher();
//        JSONObject base64 = new JSONObject();
//        base64.put("dateTime", new Date());
//        base64.put("email", teacher.getEmail());
//        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());
//
//        mockMvc.perform(get(URL + "/activate/" + b64))
//                .andExpect(status().isOk());
//    }
//}
