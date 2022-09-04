package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.enums.Mark.*;

public class FeedbackTest {
    Feedback feedback;

    public FeedbackTest() {}

    @BeforeEach
    void setUp(){
        feedback = new Feedback();
    }

    @ParameterizedTest
    @DisplayName("The evaluation of a guess and answer leads to accurate feedback")
    @MethodSource("guessAndAnswerExamples")
    void correctEvaluation(String guess, Feedback expectedFeedback) {
        String wordToGuess = "appel";

        feedback = feedback.evaluate(wordToGuess, guess);

        Assertions.assertIterableEquals(feedback.getMarks(), expectedFeedback.getMarks());
    }

    @ParameterizedTest
    @DisplayName("The word is (in)correctly guessed")
    @MethodSource("feedbackExamples")
    void wordIsGuessed(List<Mark> marks, boolean expectedGuessResult) {
        feedback.setMarks(marks);

        boolean guessResult = feedback.isWordGuessed();

        Assertions.assertEquals(guessResult, expectedGuessResult);
    }

    @Test
    @DisplayName("The generated hint contains the next letter")
    void correctHintGenerated() {
        List<Mark> marks = new ArrayList<>() {};
        Collections.addAll(marks, CORRECT, CORRECT, ABSENT, ABSENT, ABSENT);
        feedback.setMarks(marks);

        String hint = feedback.generateHint("c____", "codon");

        Assertions.assertEquals(hint, "co___");
    }

    static Stream<Arguments> guessAndAnswerExamples() {
        return Stream.of(
            Arguments.of("appel", new Feedback(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))),
            Arguments.of("oppol", new Feedback(List.of(ABSENT, CORRECT, CORRECT, ABSENT, CORRECT))),
            Arguments.of("eppap", new Feedback(List.of(PRESENT, CORRECT, CORRECT, PRESENT, ABSENT))),
            Arguments.of("4pp3l", new Feedback(List.of(INVALID, CORRECT, CORRECT, INVALID, CORRECT)))
        );
    }

    static Stream<Arguments> feedbackExamples() {
        return Stream.of(
            Arguments.of(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), true),
            Arguments.of(List.of(CORRECT, PRESENT, CORRECT, PRESENT, ABSENT), false)
        );
    }

}
