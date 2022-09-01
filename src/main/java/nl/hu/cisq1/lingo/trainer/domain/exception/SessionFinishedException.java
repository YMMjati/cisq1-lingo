package nl.hu.cisq1.lingo.trainer.domain.exception;

public class SessionFinishedException extends RuntimeException {
    public SessionFinishedException(int sessionId) {
        super("Session '"+ sessionId +"' is already finished.");
    }
}
