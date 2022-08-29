package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.*;

import java.util.List;
import java.util.UUID;

public class SessionReport {
    private final UUID id;
    private final SessionStatus status;
    private final int lastRoundNumber;
    private final int score;
    private final String lastGuess;
    private final List<Mark> lastGuessFeedback;

    public SessionReport(Session session) {
        this.id = session.getId();
        this.status = session.evaluateStatus();
        this.lastRoundNumber = session.getLastRoundNumber();
        this.score = session.evaluateScore();
        this.lastGuess = session.getLastRound().getLastGuess();
        this.lastGuessFeedback = session.evaluateFeedback();
    }

    @Override
    public String toString() {
        return "SessionReport{" +
                "id=" + id +
                ", status=" + status +
                ", lastRoundNumber=" + lastRoundNumber +
                ", score=" + score +
                ", lastGuess='" + lastGuess + '\'' +
                ", lastGuessFeedback=" + lastGuessFeedback +
                '}';
    }
}