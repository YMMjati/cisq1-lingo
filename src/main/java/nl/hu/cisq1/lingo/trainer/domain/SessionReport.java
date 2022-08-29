package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.*;

import java.util.List;
import java.util.UUID;

public class SessionReport {
    private final UUID id;
    private final SessionStatus status;
    private final int lastRoundNumber;
    private final int attempts;
    private final int score;
    private final String lastGuess;
    private final int answerLength;
    private final List<Mark> feedback;
    private final String feedbackText;
    private final String hint;
    private final String answer;

    public SessionReport(Session session) {
        this.id = session.getId();
        this.status = session.evaluateStatus();
        this.lastRoundNumber = session.getLastRoundNumber();
        this.attempts = session.getLastRound().getGuessAmount();
        this.score = session.evaluateScore();
        this.lastGuess = session.getLastRound().getLastGuess();
        this.answerLength = session.getLastRound().getAnswer().length();
        this.feedback = session.evaluateFeedback();
        this.feedbackText = this.feedbackToText(session.getLastRound().getAnswer());
        this.hint = this.generateHint(session.getLastRound().getAnswer());
        this.answer = (this.attempts >= 5) ? session.getLastRound().getAnswer() : "";
    }

    private String feedbackToText(String answer) {
        StringBuilder feedbackText = new StringBuilder();

        int i = 0;
        for (Mark mark : feedback) {
            String feedbackCharacter = switch (mark) {
                case CORRECT -> "["+ answer.charAt(i) +"]";
                case PRESENT -> "("+ this.lastGuess.charAt(i) +")";
                case ABSENT -> String.valueOf(this.lastGuess.charAt(i));
                case INVALID -> "_";
            };
            feedbackText.append(feedbackCharacter);
            i++;
        }

        return feedbackText.toString();
    }

    // This method returns the first incorrect character.
    // But only if there are 3 or more characters left to guess, and there are at least 2 previous attempts.
    private String generateHint(String answer) {
        String hint = "_";
        if (this.attempts < 3)
            return hint;

        int firstIncorrectIndex = -1;
        int correctCharacters = 0;

        int i = 0;
        for (Mark mark : this.feedback) {
            if (mark == Mark.CORRECT) {
                correctCharacters++;
                i++;
                continue;
            }
            firstIncorrectIndex = firstIncorrectIndex == -1 ? i : firstIncorrectIndex;
            i++;
        }

        if (correctCharacters >= answer.length() - 2) {
            return hint;
        }

        hint = String.valueOf(answer.charAt(firstIncorrectIndex));
        return hint;
    }


    // This method is only used for the test environment.
    public UUID getSessionId() {
        return this.id;
    }

    // This method is only used for the test environment.
    @Override
    public String toString() {
        return "SessionReport{" +
                "id=" + id +
                ", status=" + status +
                ", round=" + lastRoundNumber +
                ", attempts=" + attempts +
                ", score=" + score +
                ", lastGuess='" + lastGuess + '\'' +
                ", answerLength=" + answerLength +
                ", hint=" + hint +
                ", feedbackText='" + feedbackText + '\'' +
                ", feedback=" + feedback +
                ", answer=" + answer +
                '}';
    }
}