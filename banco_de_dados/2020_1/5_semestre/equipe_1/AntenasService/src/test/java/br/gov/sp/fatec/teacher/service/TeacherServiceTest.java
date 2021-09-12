package br.gov.sp.fatec.teacher.service;

import br.gov.sp.fatec.cadi.exception.CadiException;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.exception.TeacherException.CannotAddOrRemoveStudentsToThisProject;
import br.gov.sp.fatec.teacher.exception.TeacherException.TeacherInactiveException;
import br.gov.sp.fatec.teacher.exception.TeacherException.TeacherNotFoundException;
import br.gov.sp.fatec.teacher.repository.TeacherRepository;
import br.gov.sp.fatec.utils.commons.SendEmail;
import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import static br.gov.sp.fatec.project.fixture.ProjectFixture.newProject;
import static br.gov.sp.fatec.student.fixture.StudentFixture.newStudent;
import static br.gov.sp.fatec.teacher.fixture.TeacherFixture.newTeacher;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService service;

    @Mock
    private TeacherRepository repository;

    @Mock
    private ProjectService projectService;

    @Mock
    private SendEmail sendEmail;

    @Test
    public void save_shoudSucceed() {
        Teacher teacher = newTeacher();
        when(repository.save(teacher)).thenReturn(teacher);

        Teacher saved = service.save(teacher);

        assertEquals(teacher.getId(), saved.getId());
    }

    @Test
    public void findAll_shouldSucceed() {
        List<Teacher> teacherList = Lists.newArrayList(
                newTeacher(1L, true),
                newTeacher(2L, true),
                newTeacher(3L, true));

        when(repository.findAll()).thenReturn(teacherList);

        List<Teacher> found = service.findAll();

        assertEquals(teacherList.size(), found.size());
    }

    @Test
    public void findActive_shouldSucceed() {
        List<Teacher> teacherList = Lists.newArrayList(
                newTeacher(1L, true),
                newTeacher(2L, true),
                newTeacher(3L, true));

        when(repository.findAllByActive(true)).thenReturn(teacherList);

        List<Teacher> found = service.findActive();

        assertEquals(teacherList.size(), found.size());
    }

    @Test
    public void findById_shouldSucceed() {
        Teacher teacher = newTeacher();
        when(repository.findById(teacher.getId())).thenReturn(java.util.Optional.of(teacher));

        Teacher found = service.findById(teacher.getId());

        assertEquals(teacher.getId(), found.getId());
    }


    @Test
    public void update_shouldSucceed() {
        Teacher teacher = newTeacher();
        Teacher updated = newTeacher();
        updated.setEmail("newEmail@test.com");

        when(repository.findById(teacher.getId())).thenReturn(java.util.Optional.of(teacher));
        when(repository.save(updated)).thenReturn(updated);

        Teacher returned = service.update(teacher.getId(), updated);

        assertEquals(updated.getEmail(), returned.getEmail());
    }

//    @Test
//    public void update_shouldFail() {
//        Teacher updated = newTeacher();
//        updated.setEmail("newEmail@test.com");
//
//        when(repository.findById(2L)).thenReturn(null);
//
//        Assertions.assertThrows(TeacherNotFoundException.class, () -> {
//            service.update(2L, updated);
//        });
//    }

    @Test
    public void login_shouldSucceed() {
        Teacher teacher = newTeacher();
        when(repository.findByEmailAndPassword(teacher.getEmail(), Base64.getEncoder().encodeToString(teacher.getPassword().getBytes()))).thenReturn(teacher);
        service.login(teacher.getEmail(), teacher.getPassword());
    }

    @Test
    public void login_shouldFail_notFound() {
        Teacher teacher = newTeacher();

        Assertions.assertThrows(TeacherNotFoundException.class, () -> {
            service.login(teacher.getEmail(), teacher.getPassword());
        });
    }

    @Test
    public void login_shouldFail_Inactive() {
        Teacher teacher = newTeacher();
        teacher.setActive(false);

        when(repository.findByEmailAndPassword(teacher.getEmail(), Base64.getEncoder().encodeToString(teacher.getPassword().getBytes()))).thenReturn(teacher);

        Assertions.assertThrows(TeacherInactiveException.class, () -> {
            service.login(teacher.getEmail(), teacher.getPassword());
        });
    }

    @Test
    public void activate_shouldSucceed() throws JSONException {
        Teacher teacher = newTeacher();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", teacher.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());

        when(repository.findByEmail((String) base64.get("email"))).thenReturn(teacher);
        service.activate(b64);
        assertTrue(teacher.isActive());
    }

    @Test
    public void activate_shouldFail() throws JSONException {
        Teacher teacher = newTeacher();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", teacher.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());

        Assertions.assertThrows(TeacherNotFoundException.class, () -> {
            service.activate(b64);
        });
    }
}
