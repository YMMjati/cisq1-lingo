package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.data.WordRepository;
import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;

public class WordServiceDummy {
    private final WordRepositoryDummy wordRepositoryDummy;

    public WordServiceDummy(WordRepositoryDummy wordRepositoryDummy) {
        this.wordRepositoryDummy = new WordRepositoryDummy();
    }

    public String provideRandomWord(Integer length) {
        return this.wordRepositoryDummy
                .findRandomWordByLength(length);
                // .orElseThrow(() -> new WordLengthNotSupportedException(length)) // <-- Belongs to inbuilt Optional class?
    }

}
