package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Round {
    @Id
    private UUID id;

    private String wordToGuess;

    public Round(String wordToGuess) {
        this.id = UUID.randomUUID();
        this.wordToGuess = wordToGuess;
    }
    public Round() {}

    public void guessWord(String guess) {

    }
}
