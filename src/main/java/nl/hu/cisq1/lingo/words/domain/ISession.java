package nl.hu.cisq1.lingo.words.domain;
import nl.hu.cisq1.lingo.words.domain.enums.SessionStatus;

public interface ISession {
    public String getUUID();

    public void addRound(Round sessionRound);
    public Round getLastRound();
    public void setLastRound(Round round);

    public SessionStatus getStatus();
    public void setStatus(SessionStatus status);

    public int getScore();

    public String getLastRoundHint();
    public String getLastRoundFeedback();
}