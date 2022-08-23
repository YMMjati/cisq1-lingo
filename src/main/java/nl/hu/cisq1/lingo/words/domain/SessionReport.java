package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.enums.SessionStatus;

public class SessionReport {
    public SessionStatus status;
    int score;
    // lastRoundGuesses list

    // Add getters for every attribute

    public SessionReport(Session session) {
        this.status = evaluateStatus(session);
        this.score = session.getScore();


    }

    private SessionStatus evaluateStatus (Session session) {
        Round lastRound = session.getLastRound();
        SessionStatus status;

        if (lastRound.getGuessAmount() == 0) {
            status = SessionStatus.Initial;
        }
        else if (lastRound.lastGuessCorrect() == false && lastRound.hasGuessesLeft() == true) {
            status = SessionStatus.Playing;
        }
        else if (lastRound.lastGuessCorrect() == true) {
            status = SessionStatus.NextRound;
        }
        else {
            status = SessionStatus.Eliminated;
        }

        return status;
    }









}
