package nl.hu.cisq1.lingo.words.data.exception;

public class NonexistentSessionException extends RuntimeException {
    public NonexistentSessionException(String UUID) {
        super("Session '"+ UUID +"' does not exist in the database.");
    }
}
