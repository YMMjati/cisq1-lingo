package nl.hu.cisq1.lingo.words.data;

import nl.hu.cisq1.lingo.words.domain.Session;

import java.util.HashMap;
import java.util.UUID;

public class SessionRepositoryDummy {
    HashMap<UUID, Session> sessions = new HashMap<>();

    public Session getSession(UUID sessionId) {
        return sessions.get(sessionId);
    }

    public void newSession(Session session) {
        this.sessions.put(session.getId(), session);
    }

    public void modifySession(Session session) {
        this.sessions.replace(session.getId(), session);
    }
}