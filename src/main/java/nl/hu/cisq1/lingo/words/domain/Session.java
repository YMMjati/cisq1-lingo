package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.enums.SessionStatus;

import java.util.ArrayList;
import java.util.UUID;

public class Session implements ISession {
    public String uuid;
    public ArrayList<Round> rounds = new ArrayList<>();
    public SessionStatus status = SessionStatus.Initial; // Needless?

    public Session (Word firstAnswer) {
        this.uuid = UUID.randomUUID().toString();
        Round round = new Round(firstAnswer);
        this.addRound(round);
    }


    // getStatusReport

    @Override
    public String getUUID() {
        return this.uuid;
    }

    @Override
    public void addRound(Round round) {
        this.rounds.add(round);
    }

    @Override
    public Round getLastRound() {
        return this.rounds.get(rounds.size() - 1);
    }

    @Override
    public void setLastRound(Round round) {
        this.rounds.set(rounds.size() - 1, round);
    }

    @Override
    public SessionStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public int getScore() {
        int score = 0;

        for (Round round : rounds)
            score += round.getScore();

        return score;
    }

    @Override
    public String getLastRoundHint() {
        return "hey";
    }

    @Override
    public String getLastRoundFeedback() {
        return "hey";
    }


}