package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;

import java.util.ArrayList;

public class Round implements IRound {
    Word answer;
    ArrayList<Word> guesses = new ArrayList<>();

    public Round(Word answer) {
        this.answer = answer;
    }

    @Override
    public Word getAnswer() {
        return answer;
    }

    @Override
    public void addGuess(Word guess){
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



    // Temp!
    public String generateHint(int guessIndex) {
        return "";
    }
}