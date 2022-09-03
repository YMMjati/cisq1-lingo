package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class WordTest {

    public WordTest(){}

    @Test
    @DisplayName("length is based on given word")
    public void lengthBasedOnWord() {
        Word word = new Word("woord");
        int length = word.getLength();
        Assertions.assertEquals(5, length);
    }
}
