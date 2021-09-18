package br.gov.sp.fatec.project.exception;

import static java.lang.String.format;

public class ProjectException {

    public static class ProjectNotFoundException extends RuntimeException {
        public ProjectNotFoundException(Long id) {
            super(format("Project with id %d does not exists", id));
        }

        public ProjectNotFoundException() {
            super("Project does not exists");
        }
    }

    public static class DateDoesNotExistsException extends RuntimeException {
        public DateDoesNotExistsException() {
            super("This date is not registered");
        }
    }
}
