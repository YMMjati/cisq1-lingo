package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.enums.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.*;

import javax.persistence.*;
import java.util.*;

// This class represents the game sessions played by one or more players.
@Entity(name = "sessions")
public class Session {
    @Id
    private UUID id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Round> rounds = new ArrayList<>();
    
    public Session(String firstAnswer) {
        this.id = UUID.randomUUID();
        Round round = new Round(firstAnswer);
        this.rounds.add(round);
    }

    public Session() {

    }

    public void newRound(Round round) throws SessionEliminatedException {
        SessionStatus status = this.evaluateStatus();

        if (status == SessionStatus.ELIMINATED)
            throw new SessionEliminatedException(this.getId());

        this.rounds.add(round);
    }

    // The evaluate methods are used for retrieving the session's derived attributes.
    // SessionReport is where these results go.
    public SessionStatus evaluateStatus () {
        Round lastRound = this.getLastRound();
        SessionStatus status;

        if (lastRound.getGuessAmount() == 0)
            status = SessionStatus.PLAYING;
        else if (!lastRound.lastGuessCorrect() && lastRound.hasGuessesLeft())
            status = SessionStatus.PLAYING;
        else if (lastRound.lastGuessCorrect())
            status = SessionStatus.PLAYING;
        else
            status = SessionStatus.ELIMINATED;

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

        if (lastRound.getGuessAmount() > 0 && lastGuess.length() != answer.length()) {
            lastGuessFeedback = Collections.nCopies(answer.length(), Mark.INVALID);
            return lastGuessFeedback;
        }

        for (int i = 0; i < lettersToGuess.size(); i++) {
            if (!letters.get(i).matches("^[a-zA-Z]*$")) {
                lastGuessFeedback.add(Mark.INVALID);
                continue;
            }

            if (letters.get(i).equals(lettersToGuess.get(i))) {
                lastGuessFeedback.add(Mark.CORRECT);
                lettersToGuess.set(i, "_");
            }
            else if (lettersToGuess.contains(letters.get(i))) {
                lastGuessFeedback.add(Mark.PRESENT);

                lettersToGuess.set(lettersToGuess.indexOf(letters.get(i)), "_");
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
        return this.rounds.size();
    }

    public void setLastRound(Round round) {
        this.rounds.set(rounds.size() - 1, round);
    }
}