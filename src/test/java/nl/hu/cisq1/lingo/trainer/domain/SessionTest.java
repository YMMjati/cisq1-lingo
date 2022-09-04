package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.SessionFinishedException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class SessionTest {
    public Session session;

    public SessionTest() {}

    @BeforeEach
    void setUp(){
        session = new Session("appel");
    }

    @Test
    @DisplayName("The session's round number increases by 1 after calling the nextRound method")
    void startNextRoundCorrect() {
        session.startNextRound("fiets");
        int roundNumber = session.getLastRoundNumber();

        Assertions.assertEquals(roundNumber, 2);
    }

    @ParameterizedTest
    @MethodSource("nextLengthExamples")
    @DisplayName("The getNextWordLength method returns a number between 5-7, based on the round number")
    void getNextWordLengthCorrect(Integer roundNumber, Integer expectedLength) {
        session = new SessionStub();

        while (session.getLastRoundNumber() < roundNumber)
            session.startNextRound("dummy");
        int length = session.getNextWordLength();

        Assertions.assertEquals(length, expectedLength);
    }

    static Stream<Arguments> nextLengthExamples() {
        return Stream.of(
            Arguments.of(Integer.valueOf(1), Integer.valueOf(5)),
            Arguments.of(Integer.valueOf(2), Integer.valueOf(6)),
            Arguments.of(Integer.valueOf(3), Integer.valueOf(7)),
            Arguments.of(Integer.valueOf(4), Integer.valueOf(5))
        );
    }

    static class SessionStub extends Session {
        int lastRoundNumber;

        public SessionStub() {}

        @Override
        public int getLastRoundNumber() {
            return this.lastRoundNumber;
        }

        @Override
        public void startNextRound(String wordToGuess) throws SessionFinishedException {
            this.lastRoundNumber++;
        }
    }
}
