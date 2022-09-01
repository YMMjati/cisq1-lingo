package nl.hu.cisq1.lingo.trainer.domain.exception;

public class GuessNullException extends RuntimeException {
    public GuessNullException() {
        super("The guess is null.");
    }
}
