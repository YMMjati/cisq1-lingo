package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.SessionStatus;

import java.util.List;

public class SessionReport {
    private int id;
    private SessionStatus status;
    private String hint;
    private List<Feedback> feedbackList;
    private int score;
    private int roundNumber;
    private String answer;

    public SessionReport(int id, SessionStatus status, String hint, List<Feedback> feedbackList, int score, int roundNumber, String answer) {
        this.id = id;
        this.status = status;
        this.hint = hint;
        this.feedbackList = feedbackList;
        this.score = score;
        this.roundNumber = roundNumber;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public String getHint() {
        return hint;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public int getScore() {
        return score;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getAnswer() {
        return answer;
    }
}
