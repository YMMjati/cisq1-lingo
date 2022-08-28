package nl.hu.cisq1.lingo.words.domain.exception;

import java.util.UUID;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(UUID sessionId) {
        super("Session '"+ sessionId +"' not found.");
    }
}
