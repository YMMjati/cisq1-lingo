package nl.hu.cisq1.lingo.trainer.domain;


import javax.persistence.*;
import java.util.UUID;

@Entity(name = "guesses")
public class Guess {
    @Id
    private UUID id;
    private String value;

    public Guess(String value) {
        this.id = UUID.randomUUID();
        this.value = value;
    }

    public Guess() {}

    public String getValue() {
        return value;
    }
}
