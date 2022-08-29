package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.trainer.application.TrainerServiceDummy;
import nl.hu.cisq1.lingo.trainer.data.SessionRepositoryDummy;
import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import nl.hu.cisq1.lingo.trainer.presentation.TrainerControllerDummy;
import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.*;

// This is a Main class meant for testing and developing the game's functionalities without needing Spring.

public class TestMain {

    public static void main(String args[])
    {
        TrainerControllerDummy trainerControllerDummy = new TrainerControllerDummy();
        Scanner input = new Scanner(System.in);
        System.out.println("This is a test environment; input 'Q' to exit the application.\n");
        SessionReport sessionReport = trainerControllerDummy.newSession();


        //System.out.println(sessionReport.toString());

        //System.out.println(sessionReport.toString());




        // Game loop
        while (true) {
            break;

        }




    }

}