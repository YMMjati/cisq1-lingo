package nl.hu.cisq1.lingo.trainer.domain.exception;

public class TooManyGuessesException extends RuntimeException {
    public TooManyGuessesException() {
        super("Only 5 guesses are allowed.");
    }
}
