package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;

public class WordServiceDummy {
    private final WordRepositoryDummy wordRepositoryDummy;

    public WordServiceDummy() {
        this.wordRepositoryDummy = new WordRepositoryDummy();
    }

    public String provideRandomWord(Integer length) {
        return this.wordRepositoryDummy
                .findRandomWordByLength(length);
    }

}
