package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SessionRepositoryDummy;
import nl.hu.cisq1.lingo.words.application.WordServiceDummy;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.Session;
import nl.hu.cisq1.lingo.trainer.domain.exception.*;

import java.util.UUID;

public class TrainerServiceDummy {
    private final WordServiceDummy wordServiceDummy;
    private final SessionRepositoryDummy sessionRepositoryDummy;

    public TrainerServiceDummy() {
        this.sessionRepositoryDummy = new SessionRepositoryDummy();
        this.wordServiceDummy = new WordServiceDummy();
    }

    public UUID newSession() {
        String firstAnswer = this.wordServiceDummy.provideRandomWord(5);
        Session session = new Session(firstAnswer);
        return session.getId();
    }

    // This method is not used in the real implementation
    public Session getSessionById(UUID sessionId) throws SessionNotFoundException {
        Session session = this.sessionRepositoryDummy.getSession(sessionId);

        if (session == null) {
            throw new SessionNotFoundException(sessionId);
        }

        return session;
    }

    // This method is not used in the real implementation
    public void modifySession(Session session) throws SessionNotFoundException {
        Session oldSession = this.sessionRepositoryDummy.getSession(session.getId());

        if (oldSession == null) {
            throw new SessionNotFoundException(session.getId());
        }

        if (session.getId() != oldSession.getId()) {
            throw new SessionNotFoundException(session.getId());
        }

        this.sessionRepositoryDummy.modifySession(session);

        return;
    }


    public void guessWord(UUID sessionId, String guess) throws TooManyGuessesException {
        Session session = this.sessionRepositoryDummy.getSession(sessionId);

        if (session == null) {
            throw new SessionNotFoundException(session.getId());
        }



        // Game already over exception!


        if (session.getLastRound().hasGuessesLeft() == false) {
            throw new TooManyGuessesException();
        }

        Round lastRound = session.getLastRound();
        lastRound.addGuess(guess);
        session.setLastRound(lastRound);

        this.modifySession(session);


        // this.setSession_ById(uuid, session);
    }

    // getSessionReport() {
   ///}


}
