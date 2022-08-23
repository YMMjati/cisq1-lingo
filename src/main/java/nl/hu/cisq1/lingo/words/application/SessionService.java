package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;
import nl.hu.cisq1.lingo.words.application.WordServiceDummy;
import nl.hu.cisq1.lingo.words.domain.Round;
import nl.hu.cisq1.lingo.words.domain.Session;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.enums.SessionStatus;
import nl.hu.cisq1.lingo.words.domain.exception.TooManyGuessesException;

import java.util.HashMap;

// This class doesn't use the database at the moment, this code will be refactored later.
public class SessionService{
    private final WordServiceDummy wordServiceDummy;
    HashMap<String, Session> sessions = new HashMap<>();

    public SessionService() {
        WordRepositoryDummy wordRepositoryDummy = new WordRepositoryDummy();
        this.wordServiceDummy = new WordServiceDummy(wordRepositoryDummy);
    }

    public String generateSession() {
        Word firstAnswer = new Word(wordServiceDummy.provideRandomWord(5));
        Session session = new Session(firstAnswer);
        return session.getUUID();
    }

    public boolean sessionExists_ById(String uuid) {
        return sessions.containsKey(uuid);
    }

    public Session getSession_ById(String uuid) {
        return this.sessions.get(uuid);
    }

    public void setSession_ById(String uuid, Session session) {
        this.sessions.replace(uuid, session);
    }

    public void newGuess(String uuid, Word guess) throws TooManyGuessesException {
        Session session = this.getSession_ById(uuid);

        if (session.getLastRound().hasGuessesLeft() == false) {
            throw new TooManyGuessesException();
        }

        Round lastRound = session.getLastRound();
        lastRound.addGuess(guess);
        session.setLastRound(lastRound);
        this.setSession_ById(uuid, session);
    }

}
