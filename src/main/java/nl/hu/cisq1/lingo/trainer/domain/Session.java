package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.SessionStatus;
import nl.hu.cisq1.lingo.trainer.domain.exception.SessionFinishedException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Session {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    private SessionStatus status;

    public Session(String firstWordToGuess) {
        this.rounds.add(new Round(firstWordToGuess));
        this.status = SessionStatus.PLAYING;
    }

    public Session() {}

    public SessionReport getReport() {
        return new SessionReport(
                this.id,
                this.status,
                this.getLastRound().getHint(),
                this.getLastRound().getFeedbackList(),
                this.calculateTotalScore(),
                this.getLastRoundNumber(),
                (this.status == SessionStatus.ELIMINATED) ? this.getLastRound().getWordToGuess() : ""
        );
    }

    public void guessWord(String guess) throws SessionFinishedException {
        Round round = this.getLastRound();

        if (this.status != SessionStatus.PLAYING) {
            throw new SessionFinishedException(this.id);
        }

        round.guessWord(guess);

        if (!this.isWordGuessed() && this.getLastRound().getFeedbackList().size() >= 5) {
            this.setStatus(SessionStatus.ELIMINATED);
        }
    }

    public boolean isWordGuessed() {
        if (this.getLastRoundNumber() == 0) {
            return false;
        }

        Round round = this.getLastRound();

        return round.isWordGuessed();
    }

    public Round getLastRound() {
        return this.rounds.get(this.getLastRoundNumber() - 1);
    }

    public int calculateTotalScore() {
        int score = 0;
        for (Round round: this.rounds)
            score += round.calculateScore();
        return score;
    }

    public void startNextRound(String wordToGuess) throws SessionFinishedException {
        if (this.status != SessionStatus.PLAYING) {
            throw new SessionFinishedException(this.id);
        }

        Round nextRound = new Round(wordToGuess);
        this.rounds.add(nextRound);
    }

    public int getNextWordLength() {
        int[] lengthArray = new int[]{7, 5, 6};
        return lengthArray[this.getLastRoundNumber() % 3];
    }

    public SessionStatus getStatus() {
        return this.status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public int getLastRoundNumber() {
        return this.rounds.size();
    }
}
