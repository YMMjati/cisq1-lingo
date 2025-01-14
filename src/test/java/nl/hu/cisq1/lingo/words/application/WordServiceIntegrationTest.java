package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.data.WordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// This test doesn't work in IntelliJ because of a dependency problem:
// 'Failed to resolve org.junit.vintage:junit-vintage-engine:4.12.12'

@SpringBootTest
class WordServiceIntegrationTest {
    // Define test fixtures to be inserted before and deleted after each test
    // See: @BeforeEach and @AfterEach
    private static final String RANDOM_WORD_5 = "groep";
    private static final String RANDOM_WORD_6 = "school";
    private static final String RANDOM_WORD_7 = "student";

    @Autowired
    private WordService service;

    @Autowired
    private WordRepository repository;

    @Test
    @DisplayName("only support 5, 6 and 7 letter words")
    void unsupportedWordLength() {
        assertThrows(
                WordLengthNotSupportedException.class,
                () -> this.service.provideRandomWord(10)
        );
    }

    @Test
    @DisplayName("word exists")
    void wordExists() {
        boolean result = this.service.wordExists(RANDOM_WORD_5);
        assertTrue(result);
    }

    @Test
    @DisplayName("word does not exist")
    void wordDoesNotExist() {
        boolean result = this.service.wordExists("goner");
        assertFalse(result);
    }

    @Test
    @DisplayName("provides 5 letter word")
    void provides5LetterWord() {
        String randomWord = this.service.provideRandomWord(5);
        assertEquals(RANDOM_WORD_5, randomWord);
    }

    @Test
    @DisplayName("provides 6 letter word")
    void provides6LetterWord() {
        String randomWord = this.service.provideRandomWord(6);
        assertEquals(RANDOM_WORD_6, randomWord);
    }

    @Test
    @DisplayName("provides 7 letter word")
    void provides7LetterWord() {
        String randomWord = this.service.provideRandomWord(7);
        assertEquals(RANDOM_WORD_7, randomWord);
    }

    @BeforeEach
    void loadTestData() {
        // Load test fixtures into test database before each test case
        repository.deleteAll();
        repository.save(new Word(RANDOM_WORD_5));
        repository.save(new Word(RANDOM_WORD_6));
        repository.save(new Word(RANDOM_WORD_7));
    }

    @AfterEach
    void clearTestData() {
        // Remove test fixtures from test database after each test case
        repository.deleteAll();
    }
}
