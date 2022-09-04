package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.enums.Mark.*;


public class FeedbackTest {

    public FeedbackTest() {}
    
    @ParameterizedTest
    @MethodSource("feedbackExamples")
    @DisplayName("The word is (in)correctly guessed")
    public void wordIsGuessed(List<Mark> marks, boolean expectedGuessResult) {
        Feedback feedback = new Feedback(marks);

        boolean guessResult = feedback.isWordGuessed();

        Assertions.assertEquals(guessResult, expectedGuessResult);
    }

    public static Stream<Arguments> feedbackExamples() {
        return Stream.of(
                Arguments.of(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), true),
                Arguments.of(List.of(CORRECT, PRESENT, CORRECT, PRESENT, ABSENT), false)
        );
    }

    @Test
    @DisplayName("The generated hint contains the next letter")
    public void correctHintGenerated() {
        List<Mark> marks = new ArrayList<>() {};
        Collections.addAll(marks, CORRECT, CORRECT, ABSENT, ABSENT, ABSENT);
        Feedback feedback = new Feedback(marks);

        String hint = feedback.generateHint("c____", "codon");

        Assertions.assertEquals(hint, "co___");
    }

}
