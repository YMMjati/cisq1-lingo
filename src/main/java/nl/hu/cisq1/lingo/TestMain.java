package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import nl.hu.cisq1.lingo.trainer.presentation.TrainerControllerDummy;

import java.util.*;

// This is a Main class meant for testing and developing the game's functionalities without needing Spring.
public class TestMain {

    public static void main(String args[])
    {
        TrainerControllerDummy trainerControllerDummy = new TrainerControllerDummy();
        Scanner input = new Scanner(System.in);
        System.out.println("This is a test environment; input 'Q' to exit the application.\n");
        SessionReport sessionReport = trainerControllerDummy.newSession();
        System.out.println(sessionReport);

        while (true) {
            String inputString = input.nextLine().toLowerCase();

            if (inputString.equals("q") || inputString.equals("Q")) {
                System.out.println("\nExiting the game.");
                break;
            }

            System.out.println(trainerControllerDummy.playSession(sessionReport.getSessionId(), inputString));
        }
    }

}