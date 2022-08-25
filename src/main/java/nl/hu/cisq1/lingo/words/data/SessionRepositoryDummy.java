package nl.hu.cisq1.lingo.words.data;

import nl.hu.cisq1.lingo.words.domain.Session;

import java.util.HashMap;

public class SessionRepositoryDummy {
    HashMap<String,Session> sessions = new HashMap<String,Session>();
    WordRepositoryDummy wrd = new WordRepositoryDummy(); // Not part of the final logic!

    public SessionRepositoryDummy () {
        sessions.put("ID1", new Session(wrd.findRandomWordByLength(5)));
        sessions.put("ID2", new Session(wrd.findRandomWordByLength(5)));
        sessions.put("ID3", new Session(wrd.findRandomWordByLength(5)));
    }

    public Session findSessionByUUID(String UUID) {
        return sessions.get(UUID);
    }
}
