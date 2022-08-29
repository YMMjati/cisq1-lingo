package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.Session;
import nl.hu.cisq1.lingo.trainer.domain.enums.*;

import java.util.List;
import java.util.UUID;

public class SessionReport {
    private UUID id;
    private SessionStatus status;
    private int lastRoundNumber;
    private int score;
    private String lastGuess;
    private List<Mark> lastGuessFeedback;

    public SessionReport(Session session) {
        this.id = session.getId();
        this.status = session.evaluateStatus();
        this.lastRoundNumber = session.getLastRoundNumber();
        this.score = session.evaluateScore();
        this.lastGuess = session.getLastRound().getLastGuess();
        this.lastGuessFeedback = session.evaluateFeedback();
    }
}