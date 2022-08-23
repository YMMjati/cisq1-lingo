package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.enums.SessionStatus;

import java.util.ArrayList;
import java.util.UUID;

public class Session {
    public String uuid;
    public ArrayList<Round> rounds = new ArrayList<>();
    public SessionStatus status = SessionStatus.Initial; // Needless?

    public Session(Word firstAnswer) {
        this.uuid = UUID.randomUUID().toString();
        Round round = new Round(firstAnswer);
        this.addRound(round);
    }


    // getStatusReport

    public String getUUID() {
        return this.uuid;
    }


    public void addRound(Round round) {
        this.rounds.add(round);
    }


    public Round getLastRound() {
        return this.rounds.get(rounds.size() - 1);
    }


    public void setLastRound(Round round) {
        this.rounds.set(rounds.size() - 1, round);
    }

    public SessionStatus getStatus() {
        return this.status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public int getScore() {
        int score = 0;

        for (Round round : rounds)
            score += round.getScore();

        return score;
    }

    public String getLastRoundHint() {
        return "hey";
    }


    public String getLastRoundFeedback() {
        return "hey";
    }
}