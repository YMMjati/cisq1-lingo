package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.TrainerServiceDummy;
import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import nl.hu.cisq1.lingo.trainer.domain.exception.SessionNotFoundException;

import java.util.UUID;

// This is a test variant of TrainerController, it may be phased out later.
public class TrainerControllerDummy {
    private final TrainerServiceDummy trainerServiceDummy;

    public TrainerControllerDummy () {
        this.trainerServiceDummy = new TrainerServiceDummy();
    }

    public SessionReport newSession() {
        return this.trainerServiceDummy.startSession();
    }

    public SessionReport playSession(UUID sessionId, String guess) throws SessionNotFoundException {
        return this.trainerServiceDummy.guessWord(sessionId, guess);
    }

    public SessionReport sessionInfo(UUID sessionId) throws SessionNotFoundException {
        return this.trainerServiceDummy.getSessionReport(sessionId);
    }
}