package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SessionRepositoryDummy;
import nl.hu.cisq1.lingo.trainer.domain.exception.SessionNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.Session;
import nl.hu.cisq1.lingo.trainer.domain.exception.*;
import nl.hu.cisq1.lingo.words.presentation.RandomWordControllerDummy;

import java.util.UUID;

public class TrainerServiceDummy {
    private final RandomWordControllerDummy wordControllerDummy;
    private final SessionRepositoryDummy sessionRepositoryDummy;

    public TrainerServiceDummy() {
        this.sessionRepositoryDummy = new SessionRepositoryDummy();
        this.wordControllerDummy = new RandomWordControllerDummy();
    }

    public SessionReport startSession() {
        String firstAnswer = this.wordControllerDummy.getRandomWord(5);
        Session session = new Session(firstAnswer);
        this.sessionRepositoryDummy.newSession(session);

        return new SessionReport(session);
    }

    public SessionReport guessWord(UUID sessionId, String guess) throws SessionNotFoundException, TooManyGuessesException {
        Session session = this.sessionRepositoryDummy.getSession(sessionId);

        if (session == null)
            throw new SessionNotFoundException(sessionId);

        if (session == null)
            throw new SessionNotFoundException(sessionId);

        Round lastRound = session.getLastRound();

        if (!lastRound.hasGuessesLeft())
            throw new TooManyGuessesException();

        lastRound.addGuess(guess);
        session.setLastRound(lastRound);

        if (lastRound.lastGuessCorrect()) {
            int[] lengthArray = new int[]{7, 5, 6};
            int roundNumber = session.getLastRoundNumber();
            int wordLength = (roundNumber != 0) ? roundNumber % 3 : 1;
            String answer = this.wordControllerDummy.getRandomWord(lengthArray[wordLength]);
            Round round = new Round(sessionId, answer);
            session.newRound(round);
        }

        this.sessionRepositoryDummy.modifySession(session);

        return new SessionReport(session);
    }

    public SessionReport getSessionReport(UUID sessionId) throws SessionNotFoundException {
        Session session = this.sessionRepositoryDummy.getSession(sessionId);

        if (session == null)
            throw new SessionNotFoundException(sessionId);

        return new SessionReport(session);
    }

}