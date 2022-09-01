package nl.hu.cisq1.lingo.trainer.domain.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(int sessionId) {
        super("Session '"+ sessionId +"' not found.");
    }
}
