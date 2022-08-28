package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.words.application.TrainerServiceDummy;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SessionRepositoryDummy;
import nl.hu.cisq1.lingo.words.data.WordRepository;
import nl.hu.cisq1.lingo.words.data.WordRepositoryDummy;
import nl.hu.cisq1.lingo.words.domain.Session;
import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.enums.Mark;
import nl.hu.cisq1.lingo.words.domain.exception.TooManyGuessesException;

import java.util.*;

// This is a Main class meant for testing and developing the game's functionalities, without needing Spring.

public class TestMain {

    public static void main(String args[])
    {
        SessionRepositoryDummy srd = new SessionRepositoryDummy();

        // Session testsession = srd.findSessionByUUID("Haribo");

        /*
        if (testsession == null) {
            System.out.println("Session does not exist!\n");
        }
        */


        TrainerServiceDummy trainerServiceDummy = new TrainerServiceDummy();

        int guesses = 1;
        int guessLimit = 5;
        int round = 1;
        int[] lengthArray = new int[]{7, 5, 6};
        int wordLength = (round != 0) ? round % 3 : 1;

        WordRepositoryDummy wrd = new WordRepositoryDummy();

        String answer = wrd.findRandomWordByLength(lengthArray[wordLength]);

        String answerPreview = answer.substring(0,1) + "_".repeat(answer.length() - 1);

        System.out.println("\n"+ guesses +"/"+ guessLimit +" Guesses | "+ answerPreview +" | ("+ answer +") | Round: "+ round);



        Scanner input = new Scanner(System.in);


        // This list contains the inputted word's feedback per character
        ArrayList<Mark> feedbackList = new ArrayList<>();


        System.out.println("This is a test environment; input 'Q' to exit the application.");

        while (true){
            String inputString = input.nextLine().toLowerCase();
            Word inputWord = new Word(inputString);
            guesses++;


            // This part of the code won't be in the real implementation!
            System.out.println("\n"+ guesses +"/"+ guessLimit +" Guesses | "+ answerPreview +" | ("+ answer +") | Round: "+ round);


            if (inputString.equals("q") || inputString.equals("Q")) {
                System.out.println("\nExiting the game. The correct answer was "+ answer +".");
                break;
            }

            if (guesses >= 5) {
                System.out.println("\nThe correct answer was "+ answer +".");
                break;
            }

            if (inputString.length() != answer.length()) {
                System.out.println("Invalid length!");
                continue;
            }

            if (!inputWord.hasValidCharacters()) {
                System.out.println("Invalid characters!");

                // If just one character is invalid, all characters will be counted as invalid.
                for (int x = 0; x < answer.length(); x++)
                    feedbackList.add(Mark.Invalid);

                continue;
            }

            if (inputWord.equals(new Word(answer))) {
                System.out.println("\nCorrect! (input Q to stop)");

                guesses = 1;
                round++;
                wordLength = (round != 0) ? round % 3 : 1;
                answer = wrd.findRandomWordByLength(lengthArray[wordLength]);
                answerPreview = answer.substring(0,1) + "_".repeat(answer.length() - 1);
                System.out.println("\n"+ guesses +"/"+ guessLimit +" Guesses | "+ answerPreview +" | ("+ answer +") | Round: "+ round);

                continue;
            }

            ArrayList<Character> answerCharacters = new ArrayList<>();
            for (int x = 0; x < answer.length(); x++)
                answerCharacters.add(answer.charAt(x));

            // Iterates through the inputted word and compares it by character
            for (int x = 0; x < answer.length(); x++) {
                Mark mark;

                if (inputWord.getValue().charAt(x) == answer.charAt(x)) {
                    mark = Mark.Correct;
                }
                else if (answerCharacters.contains(inputWord.getValue().charAt(x))) {
                    mark = Mark.Present;
                }
                else {
                    mark = Mark.Absent;
                }

                System.out.print(mark +", ");

                feedbackList.add(mark);
            }

            guesses++;


        }

    }

}