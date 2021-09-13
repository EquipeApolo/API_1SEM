package br.gov.sp.fatec.student.exception;

import static java.lang.String.format;

public class StudentException {

    public static class StudentInactiveException extends RuntimeException {
        public StudentInactiveException(Long id) {
            super(format("The student with the id '%d' is inactive", id));
        }
    }

    public static class StudentNotFoundException extends RuntimeException {
        public StudentNotFoundException(Long id) {
            super(format("The student with the id '%d' does not exists", id));
        }

        public StudentNotFoundException() {
            super("The student  does not exists");
        }
    }

    public static class StudentLoginFailed extends RuntimeException {
        public StudentLoginFailed() {
            super("Student - login failed");
        }
    }

    public static class PostSolutionFailedException extends RuntimeException {
        public PostSolutionFailedException() {
            super("You have no permission to post solution to this project");
        }
    }
}
