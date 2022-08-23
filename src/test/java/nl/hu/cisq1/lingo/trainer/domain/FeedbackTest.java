package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.enums.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("shows word is guessed")
    public void guessIsCorrect() {
        String wordToGuess = "orange";
        String guess = "orange";
        Feedback feedback = Feedback.evaluate(wordToGuess, guess);

        boolean result = feedback.isWordGuessed();

        assertTrue(result);
    }

    @Test
    @DisplayName("shows word is not guessed")
    public void guessIsNotCorrect() {
        String wordToGuess = "orange";
        String guess = "apples";
        Feedback feedback = Feedback.evaluate(wordToGuess, guess);

        boolean result = feedback.isWordGuessed();

        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("feedbackExamples")
    @DisplayName("gives the right feedback for given word to guess and guess")
    public void correctFeedback(String wordToGuess, String guess, List<Mark> expectedMarks) {
        Feedback feedback = Feedback.evaluate(wordToGuess, guess);
        List<Mark> actualMarks = feedback.getMarks();

        assertEquals(expectedMarks, actualMarks);

    }

    public static Stream<Arguments> feedbackExamples() {
        return Stream.of(
                Arguments.of("baard", "bergen", List.of(INVALID, INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("baard", "bonje", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT))
        );
    }
}