package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.enums.SessionStatus;
import nl.hu.cisq1.lingo.words.domain.exception.TooManyGuessesException;

import java.util.ArrayList;
import java.util.UUID;

public class Session {
    private UUID id;
    private ArrayList<Round> rounds = new ArrayList<>();
    private SessionStatus status = SessionStatus.Initial; // Playing

    public Session(String firstAnswer) {
        this.id = UUID.randomUUID();
        Round round = new Round(firstAnswer);
        this.addRound(round);
    }

    public void guessWord(String guess) throws TooManyGuessesException {
        if (!this.getLastRound().hasGuessesLeft()) {
            throw new TooManyGuessesException();
        }

        Round round = this.rounds.get(rounds.size() - 1);
        round.addGuess(guess);
        this.setLastRound(round);
    }

    public void newRound(String answer) {
        Round nextRound = new Round(answer);
        this.rounds.add(nextRound);
    }

    public UUID getId() {
        return this.id;
    }

    public SessionStatus getStatus() {
        return this.status;
    }

    public Round getLastRound() {
        return this.rounds.get(rounds.size() - 1);
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public void setLastRound(Round round) {
        this.rounds.set(rounds.size() - 1, round);
    }
    public void addRound(Round round) {
        this.rounds.add(round);
    }
}