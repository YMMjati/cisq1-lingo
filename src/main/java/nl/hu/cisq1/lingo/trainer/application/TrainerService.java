package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SessionRepository;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.Session;
import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import nl.hu.cisq1.lingo.trainer.domain.exception.SessionNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.TooManyGuessesException;
import nl.hu.cisq1.lingo.words.presentation.RandomWordController;
import nl.hu.cisq1.lingo.words.presentation.RandomWordControllerDummy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class TrainerService {
    private final SessionRepository sessionRepository;
    private final RandomWordController randomWordController; // Will use this later, after the dummy works

    public TrainerService(SessionRepository sessionRepository, RandomWordController randomWordController) {
        this.sessionRepository = sessionRepository;
        this.randomWordController = randomWordController;
    }

    public SessionReport startSession() {
        RandomWordControllerDummy randomWordControllerDummy = new RandomWordControllerDummy();
        String firstAnswer = randomWordControllerDummy.getRandomWord(5);
        Session session = new Session(firstAnswer);
        this.sessionRepository.save(session);

        return new SessionReport(session);
    }

    public SessionReport guessWord(UUID sessionId, String guess) throws SessionNotFoundException, TooManyGuessesException {
        RandomWordControllerDummy randomWordControllerDummy = new RandomWordControllerDummy();
        Session session = this.sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(sessionId));
        Round lastRound = session.getLastRound();

        if (!lastRound.hasGuessesLeft())
            throw new TooManyGuessesException();

        lastRound.addGuess(guess);
        session.setLastRound(lastRound);

        if (lastRound.lastGuessCorrect()) {
            int[] lengthArray = new int[]{7, 5, 6};
            int roundNumber = session.getLastRoundNumber();
            int wordLength = roundNumber % 3;
            String answer = randomWordControllerDummy.getRandomWord(lengthArray[wordLength]);
            Round round = new Round(answer);
            session.newRound(round);
        }

        this.sessionRepository.save(session);

        return new SessionReport(session);
    }

    public SessionReport getSessionReport(UUID sessionId) {
        Session session = this.sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(sessionId));

        return new SessionReport(session);
    }
}
