package nl.hu.cisq1.lingo.trainer.domain;


import javax.persistence.*;
import java.util.UUID;

// This class represents the guess attempts within a round.
@Entity(name = "guesses")
public class Guess {
    @Id
    @GeneratedValue
    private UUID id;
    private String value;

    public Guess(String value) {
        this.value = value;
    }

    public Guess() {}

    public String getValue() {
        return value;
    }
}
