package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.enums.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.*;

import java.util.*;

public class Session {
    private UUID id;
    private ArrayList<Round> rounds = new ArrayList<>();
    
    public Session(String firstAnswer) {
        this.id = UUID.randomUUID();
        Round round = new Round(this.id, firstAnswer);
        this.newRound(round);
    }


    public boolean guessWord(String guess) throws TooManyGuessesException {
        if (!this.getLastRound().hasGuessesLeft()) {
            throw new TooManyGuessesException();
        }

        Round round = this.rounds.get(rounds.size() - 1);
        round.addGuess(guess);
        this.setLastRound(round);

        if (round.lastGuessCorrect()) {
            return true;
        }

        // Evaluate sessionStatus in SessionService!

        return false;
    }

    public void newRound(Round round) throws SessionEliminatedException {
        SessionStatus status = this.evaluateStatus();

        if (status == SessionStatus.ELIMINATED) {
            throw new SessionEliminatedException(this.getId());
        }

        this.rounds.add(round);
    }

    public SessionStatus evaluateStatus () {
        Round lastRound = this.getLastRound();
        SessionStatus status;

        if (lastRound.getGuessAmount() == 0) {
            status = SessionStatus.PLAYING;
        }
        else if (lastRound.lastGuessCorrect() == false && lastRound.hasGuessesLeft() == true) {
            status = SessionStatus.PLAYING;
        }
        else if (lastRound.lastGuessCorrect() == true) {
            status = SessionStatus.PLAYING;
        }
        else {
            status = SessionStatus.ELIMINATED;
        }

        return status;
    }

    public int evaluateScore() {
        int score = 0;

        for (Round round : this.rounds) {
            score += round.getScore();
        }

        return score;
    }

    public List<Mark> evaluateFeedback() {
        Round lastRound = this.getLastRound();
        String answer = lastRound.getAnswer();
        String lastGuess = lastRound.getLastGuess();

        List<Mark> lastGuessFeedback = new ArrayList<>();
        List<String> lettersToGuess = Arrays.asList(answer.split(""));
        List<String> letters = Arrays.asList(lastGuess.split(""));

        if (lastGuess.length() != answer.length() || !lastGuess.matches("^[a-zA-Z]*$")) {
            lastGuessFeedback = Collections.nCopies(answer.length(), Mark.INVALID);
            return lastGuessFeedback;
        }

        for (int i = 0; i < lettersToGuess.size(); i++) {
            if (letters.get(i).equals(lettersToGuess.get(i))) {
                lastGuessFeedback.add(Mark.CORRECT);
                lettersToGuess.set(i, "_");
            }
            if (!letters.get(i).equals(lettersToGuess.get(i)) && lettersToGuess.contains(i)) {
                lastGuessFeedback.add(Mark.PRESENT);
                lettersToGuess.set(i, "_");
            }
            else {
                lastGuessFeedback.add(Mark.ABSENT);
            }
        }

        return lastGuessFeedback;
    }


    public UUID getId() {
        return this.id;
    }

    public Round getLastRound() {
        return this.rounds.get(rounds.size() - 1);
    }

    public int getLastRoundNumber() {
        return rounds.size();
    }

    public void setLastRound(Round round) {
        this.rounds.set(rounds.size() - 1, round);
    }
}