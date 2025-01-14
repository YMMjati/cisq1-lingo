package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.data.WordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is a unit test.
 *
 * It tests the behaviors of our system under test,
 * WordService, in complete isolation:
 * - its methods are called by the test framework instead of a controller
 * - the WordService calls a test double instead of an actual repository
 */

// This test doesn't work in IntelliJ because of a dependency problem:
// 'Failed to resolve org.junit.vintage:junit-vintage-engine:4.12.12'

class WordServiceTest {
    @Test
    @DisplayName("throws exception if length not supported")
    void unsupportedLength() {
        WordRepository mockRepository = mock(WordRepository.class);
        when(mockRepository.findRandomWordByLength(anyInt()))
                .thenReturn(Optional.empty());

        WordService service = new WordService(mockRepository);

        assertThrows(
                WordLengthNotSupportedException.class,
                () -> service.provideRandomWord(5)
        );
    }

    @Test
    @DisplayName("word exists")
    void wordExists() {
        WordRepository mockRepository = mock(WordRepository.class);
        when(mockRepository.existsById("groep")).thenReturn(true);

        WordService service = new WordService(mockRepository);
        boolean result = service.wordExists("groep");

        assertTrue(result);
    }

    @Test
    @DisplayName("word does not exist")
    void wordDoesNotExist() {
        WordRepository mockRepository = mock(WordRepository.class);

        WordService service = new WordService(mockRepository);
        boolean result = service.wordExists("groep");

        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("requests a random word of a specified length from the repository")
    @MethodSource("randomWordExamples")
    void providesRandomWord(int wordLength, String word) {
        WordRepository mockRepository = mock(WordRepository.class);
        when(mockRepository.findRandomWordByLength(wordLength))
                .thenReturn(Optional.of(new Word(word)));

        WordService service = new WordService(mockRepository);
        String result = service.provideRandomWord(wordLength);

        assertEquals(word, result);
    }

    static Stream<Arguments> randomWordExamples() {
        return Stream.of(
                Arguments.of(5, "groep"),
                Arguments.of(6, "school"),
                Arguments.of(7, "student")
        );
    }
}