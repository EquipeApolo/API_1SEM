package br.gov.sp.fatec.utils.exception;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.exception.CadiException.CadiLoginFailed;
import br.gov.sp.fatec.cadi.exception.CadiException.CadiNotFoundException;
import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import br.gov.sp.fatec.entrepreneur.exception.EntrepreneurException.EntrepreneurNotFoundException;
import br.gov.sp.fatec.project.domain.Date;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.exception.ProjectException.*;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.exception.StudentException.StudentNotFoundException;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.exception.TeacherException.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Throwable {
    public NotFoundException() {
        super("Resource not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public static void throwIfNull(Object obj) throws NotFoundException {
        if (obj == null) {
            throw new NotFoundException();
        }
    }

    public static void throwIfStudentIsNull(Student student, Long id) throws StudentNotFoundException {
        if (student == null) {
            throw new StudentNotFoundException(id);
        }
    }
    public static void throwIfStudentIsNull(Student student) throws StudentNotFoundException {
        if (student == null) {
            throw new StudentNotFoundException();
        }
    }

    public static void throwIfTeacherIsNull(Teacher teacher, Long id) throws TeacherNotFoundException {
        if (teacher == null) {
            throw new TeacherNotFoundException(id);
        }
    }

    public static void throwIfTeacherIsNull(Teacher teacher) throws TeacherNotFoundException {
        if (teacher == null) {
            throw new TeacherNotFoundException();
        }
    }

    public static void throwIfEntrepreneurIsNull(Entrepreneur entrepreneur, Long id) throws EntrepreneurNotFoundException {
        if (entrepreneur == null) {
            throw new EntrepreneurNotFoundException(id);
        }
    }

    public static void throwIfDateIsNull(Date date) throws DateDoesNotExistsException {
        if (date == null) {
            throw new DateDoesNotExistsException();
        }
    }

    public static void throwIfEntrepreneurIsNull(Entrepreneur entrepreneur) throws EntrepreneurNotFoundException {
        if (entrepreneur == null) {
            throw new EntrepreneurNotFoundException();
        }
    }

    public static void throwIfCadiIsNull(Cadi cadi, Long id) throws CadiNotFoundException {
        if (cadi == null) {
            throw new CadiNotFoundException(id);
        }
    }

    public static void throwIfCadiIsNull(Cadi cadi) throws CadiNotFoundException {
        if (cadi == null) {
            throw new CadiNotFoundException();
        }
    }

    public static void throwIfProjectIsNull(Project project, Long id) throws ProjectNotFoundException {
        if (project == null) {
            throw new ProjectNotFoundException(id);
        }
    }

    public static void throwIfProjectIsNull(Project project) throws ProjectNotFoundException {
        if (project == null) {
            throw new ProjectNotFoundException();
        }
    }

    public static void throwIfNull(Object obj, String message) throws NotFoundException {
        if (obj == null) {
            throw new NotFoundException(message);
        }
    }

    public static void throwIf(boolean condition, String message) throws NotFoundException {
        if (condition) {
            throw new NotFoundException(message);
        }
    }

    public static void throwIf(boolean condition) throws NotFoundException {
        if (condition) {
            throw new NotFoundException();
        }
    }
}
