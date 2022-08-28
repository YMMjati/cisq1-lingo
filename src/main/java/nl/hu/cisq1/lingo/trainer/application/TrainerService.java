package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SessionRepository;
import nl.hu.cisq1.lingo.trainer.domain.Session;
import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import nl.hu.cisq1.lingo.trainer.domain.exception.SessionNotFoundException;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TrainerService {
    private SessionRepository sessionRepository;
    private WordService wordService;

    public TrainerService(SessionRepository sessionRepository, WordService wordService) {
        this.sessionRepository = sessionRepository;
        this.wordService = wordService;
    }

    public SessionReport startSession() {
        String wordToGuess = wordService.provideRandomWord(5);

        Session session = new Session(wordToGuess);
        this.sessionRepository.save(session);

        return session.getReport();
    }

    public SessionReport guessWord(UUID sessionId, String guess) {
        Session session = this.sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(sessionId));

        session.guessWord(guess);
        // start next round if won with word of next length?

        this.sessionRepository.save(session);

        return session.getReport();
    }

    public SessionReport getSessionReport(UUID sessionId) {
        Session session = this.sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException(sessionId));

        return session.getReport();
    }
}
