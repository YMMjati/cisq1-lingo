package nl.hu.cisq1.lingo.words.domain;

public interface IRound {
    public Word getAnswer();
    public void addGuess(Word guess);
    public String generateHint(int guessIndex);
}
