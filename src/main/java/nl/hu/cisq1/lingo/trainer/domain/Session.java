package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.SessionStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Session {
    @Id
    private UUID id;

    @OneToMany
    private List<Round> rounds = new ArrayList<>();

    private SessionStatus status;

    public Session(String firstWordToGuess) {
        this.id = UUID.randomUUID();
        this.rounds.add(new Round(firstWordToGuess));
        this.status = SessionStatus.PLAYING;
    }

    public Session() {}

    public SessionReport getReport() {
        return new SessionReport(this);
    }

    public void guessWord(String guess) {
        Round round = this.rounds.get(rounds.size() - 1);
        round.guessWord(guess);
    }
}
