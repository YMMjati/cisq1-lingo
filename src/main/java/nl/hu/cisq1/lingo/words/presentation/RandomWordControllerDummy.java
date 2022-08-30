package nl.hu.cisq1.lingo.words.presentation;

import nl.hu.cisq1.lingo.words.application.WordServiceDummy;

// This is a temporary variant of RandomWordController, meant for testing.
public class RandomWordControllerDummy {
    private final WordServiceDummy wordServiceDummy;

    public RandomWordControllerDummy () {
        this.wordServiceDummy = new WordServiceDummy();
    }

    public String getRandomWord(int length) {
        return this.wordServiceDummy.provideRandomWord(length);
    }
}
