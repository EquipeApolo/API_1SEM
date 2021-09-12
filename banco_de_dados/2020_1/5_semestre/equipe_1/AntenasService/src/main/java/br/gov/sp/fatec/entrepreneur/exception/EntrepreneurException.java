package br.gov.sp.fatec.entrepreneur.exception;

import static java.lang.String.format;

public class EntrepreneurException {

    public static class EntrepreneurNotFoundException extends RuntimeException {
        public EntrepreneurNotFoundException (Long id) {
            super(format("Entrepreneur with id %d does not exists", id));
        }

        public EntrepreneurNotFoundException () {
            super("Entrepreneu does not exists");
        }
    }

    public static class EntrepreneurInactiveException extends RuntimeException {
        public EntrepreneurInactiveException (Long id) {
            super(format("Entrepreneur with id %d is inactive", id));
        }
    }

    public static class EntrepreneurLoginFailed extends RuntimeException {
        public EntrepreneurLoginFailed() {
            super("Entrepreneur - login failed");
        }
    }
}
