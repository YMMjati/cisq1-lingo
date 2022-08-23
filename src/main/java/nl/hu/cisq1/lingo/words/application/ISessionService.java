package nl.hu.cisq1.lingo.words.application;

import nl.hu.cisq1.lingo.words.domain.Session;

public interface ISessionService {
    public String generateSession();


    public boolean sessionExists_ById(String uuid);
    public Session getSession_ById(String uuid);
    public void setSession_ById(String uuid, Session session);




    // JsonObject object = Json.createObjectBuilder().build();

    // public <SessionStatus, Session> reviewGuess (String guess);




    //     public Session () {
    //        this.uuid = UUID.randomUUID().toString();
    //    }



}
