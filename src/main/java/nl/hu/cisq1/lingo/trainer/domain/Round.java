package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;

import java.util.ArrayList;
import java.util.UUID;

public class Round {
    UUID id;
    String answer;
    ArrayList<String> guesses = new ArrayList<>();

    public Round(UUID id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void addGuess(String guess){
        this.guesses.add(guess);
    }

    public boolean hasGuessesLeft() {
        return (this.guesses.size() < 5);
    }

    public int getGuessAmount () {
        return this.guesses.size();
    }

    public boolean lastGuessCorrect() {
        return this.getLastGuess().equals(this.answer);
    }

    public int getScore () {
        return (this.lastGuessCorrect()) ? (5 * (5 - guesses.size())) + 5 : 0;
    }

    public String getLastGuess() {
        return this.guesses.get(this.guesses.size() - 1);
    }
}