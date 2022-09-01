package nl.hu.cisq1.lingo.trainer.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Round {
    @Id
    @GeneratedValue
    private int id;

    private String wordToGuess;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Feedback> feedbackList = new ArrayList<>();

    private String hint;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.feedbackList = new ArrayList<>();
        this.hint = wordToGuess.charAt(0) + ".".repeat(wordToGuess.length() - 1);
    }
    public Round() {}

    public void guessWord(String guess) {
        Feedback feedback = Feedback.evaluate(wordToGuess, guess);
        this.feedbackList.add(feedback);
        this.hint = feedback.generateHint(this.hint, wordToGuess);
    }

    public boolean isWordGuessed() {
        if (feedbackList.size() == 0) {
            return false;
        }

        Feedback lastfeedback = feedbackList.get(feedbackList.size() - 1);

        return lastfeedback.isWordGuessed();
    }

    public String getHint() {
        return this.hint;
    }

    public List<Feedback> getFeedbackList() {
        return this.feedbackList;
    }

    public String getWordToGuess() {
        return this.wordToGuess;
    }

    public int calculateScore() {
        return (this.isWordGuessed()) ? (5 * (5 - feedbackList.size())) + 5 : 0;
    }
}
