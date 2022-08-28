package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;

import java.util.ArrayList;

public class Round {
    String answer;
    ArrayList<String> guesses = new ArrayList<>();

    public Round(String answer) {
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
        return this.guesses.get(this.guesses.size() - 1).equals(this.answer);
    }

    public int getScore () {
        return (this.lastGuessCorrect()) ? (5 * (5 - guesses.size())) + 5 : 0;
    }


}