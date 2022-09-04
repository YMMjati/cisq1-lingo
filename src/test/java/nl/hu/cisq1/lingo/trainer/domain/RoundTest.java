package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;


import static nl.hu.cisq1.lingo.trainer.domain.enums.Mark.*;

public class RoundTest {
    Round round;

    public RoundTest() {}

    @BeforeEach
    void setUp(){
        round = new Round("appel");
    }

    @Test
    @DisplayName("The Round object's feedbackList attribute is correctly updated after a guess attempt")
    void guessWord_feedbackIsCorrect() {
        String guess = "apfel";

        round.guessWord(guess);

        Assertions.assertEquals(round.getFeedbackList().size(), 1);
    }

    @Test
    @DisplayName("The Round object's hint attribute is correctly updated after a guess attempt")
    void guessWord_HintIsCorrect() {
        String guess = "apfel";

        round.guessWord(guess);

        Assertions.assertNotEquals(round.getHint(), "");
    }

    @ParameterizedTest
    @DisplayName("The correct boolean is given when asking the Round if the word is guessed")
    @MethodSource("guessExamples")
    void isWordGuessedCorrect(List<Feedback> feedbackList, Boolean expectedBoolean) {
        Boolean result;

        if (feedbackList != null)
            round.setFeedbackList(feedbackList);
        result = round.isWordGuessed();

        Assertions.assertEquals(result, expectedBoolean);
    }

    @ParameterizedTest
    @DisplayName("The correct score is given when asking the Round to calculate it")
    @MethodSource("scoreExamples")
    void calculatedScoreCorrect(Boolean wordGuessed, List<Feedback> feedbackList, int expectedScore) {
        round = new RoundStub(wordGuessed);
        round.setFeedbackList(feedbackList);

        int score = round.calculateScore();

        Assertions.assertEquals(score, expectedScore);
    }

    static Stream<Arguments> guessExamples() {
        return Stream.of(
            Arguments.of(null, false),
            Arguments.of(List.of(new Feedback(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))), true),
            Arguments.of(List.of(new Feedback(List.of(ABSENT, CORRECT, CORRECT, CORRECT, CORRECT))), false)
        );
    }

    static Stream<Arguments> scoreExamples() {
        return Stream.of(
                Arguments.of(false, List.of(new Feedback(List.of()), new Feedback(List.of()), new Feedback(List.of())), 0),
                Arguments.of(true, List.of(new Feedback(List.of()), new Feedback(List.of()), new Feedback(List.of())), 15)
        );
    }

    static class RoundStub extends Round {
        Boolean wordGuessed;

        public RoundStub(Boolean wordGuessed) {
            this.wordGuessed = wordGuessed;
        }

        public boolean isWordGuessed() {
            return wordGuessed;
        }
    }
}
