package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.*;

// This class represents the rounds that a game session consists of.
@Entity(name = "rounds")
public class Round {
    @Id
    @GeneratedValue
    private UUID id;
    private String answer;

    @OneToMany(cascade = {CascadeType.ALL})
    List<Guess> guesses = new ArrayList<>();

    public Round(String answer) {
        this.answer = answer;
    }

    public Round() {

    }

    public void addGuess(String guessString){
        this.guesses.add(new Guess(guessString));
    }

    public boolean hasGuessesLeft() {
        return (this.guesses.size() < 5);
    }

    public boolean lastGuessCorrect() {
        return this.getLastGuess().equals(this.answer);
    }

    public String getAnswer() {
        return answer;
    }

    public int getScore () {
        return (this.lastGuessCorrect()) ? (5 * (5 - guesses.size())) + 5 : 0;
    }

    public String getLastGuess() {
        return this.guesses.size() >= 1 ? this.guesses.get(this.guesses.size() - 1).getValue() : this.answer.substring(0, 1) + "_".repeat(answer.length() - 1);
    }

    public int getGuessAmount () {
        return this.guesses.size();
    }
}