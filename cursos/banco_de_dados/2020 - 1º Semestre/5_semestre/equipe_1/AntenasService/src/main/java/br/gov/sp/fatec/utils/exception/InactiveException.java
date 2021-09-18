package br.gov.sp.fatec.utils.exception;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.exception.CadiException.CadiInactiveException;
import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import br.gov.sp.fatec.entrepreneur.exception.EntrepreneurException.EntrepreneurInactiveException;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.exception.StudentException.StudentInactiveException;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.exception.TeacherException.TeacherInactiveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class InactiveException {

    public static void throwIfStudentIsInactive(Student student) throws StudentInactiveException {
        if (!student.isActive()) {
            throw new StudentInactiveException(student.getId());
        }
    }
    public static void throwIfTeacherIsInactive(Teacher teacher) throws TeacherInactiveException {
        if (!teacher.isActive()) {
            throw new TeacherInactiveException(teacher.getId());
        }
    }
    public static void throwIfEntrepreneurIsInactive(Entrepreneur entrepreneur) throws EntrepreneurInactiveException {
        if (!entrepreneur.isActive()) {
            throw new EntrepreneurInactiveException(entrepreneur.getId());
        }
    }
    public static void throwIfCadiIsInactive(Cadi cadi) throws CadiInactiveException {
        if (!cadi.isActive()) {
            throw new CadiInactiveException(cadi.getId());
        }
    }
}
