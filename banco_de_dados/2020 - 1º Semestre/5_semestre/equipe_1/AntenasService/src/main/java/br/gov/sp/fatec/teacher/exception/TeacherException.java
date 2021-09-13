package br.gov.sp.fatec.teacher.exception;

import static java.lang.String.format;

public class TeacherException {
    public static class TeacherNotFoundException extends RuntimeException {
        public TeacherNotFoundException(Long id) {
            super(format("Teacher with id %d does not exists", id));
        }
        public TeacherNotFoundException() {
            super("Teacher does not exists");
        }
    }

    public static class TeacherInactiveException extends RuntimeException {
        public TeacherInactiveException(Long id) {
            super(format("Teacher with id %d is inactive", id));
        }
    }

    public static class TeacherLoginFailed extends RuntimeException {
        public TeacherLoginFailed() {
            super("Teacher - login failed");
        }
    }

    public static class CannotAddOrRemoveStudentsToThisProject extends RuntimeException {
        public CannotAddOrRemoveStudentsToThisProject() {
            super("This teacher has no permission to add or remove students to this project");
        }
    }
}
