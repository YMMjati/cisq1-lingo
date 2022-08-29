package nl.hu.cisq1.lingo.trainer.domain.exception;

import java.util.UUID;

public class SessionEliminatedException extends RuntimeException {
    public SessionEliminatedException(UUID sessionId) {
        super("Session '"+ sessionId +"' cannot be played as it is already finished.");
    }
}
