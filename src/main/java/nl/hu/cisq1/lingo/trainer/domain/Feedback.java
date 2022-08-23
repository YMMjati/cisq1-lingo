package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// TODO: check workshop sheets voor persistentie
public class Feedback {
    private List<Mark> marks = new ArrayList<>();

    public Feedback(List<Mark> marks) {
        this.marks = marks;
    }

    public static Feedback evaluate(String wordToGuess, String guess) {
        List<Mark> marks = new ArrayList<>();

        // invalid characters? --> INVALID

        if (wordToGuess.length() != guess.length()) {
            marks = Collections.nCopies(wordToGuess.length(), Mark.INVALID);
            return new Feedback(marks);
        }

        List<String> lettersToGuess = Arrays.asList(wordToGuess.split(""));
        List<String> letters = Arrays.asList(guess.split(""));

        for (int i = 0; i < lettersToGuess.size(); i++) {
            if (letters.get(i).equals(lettersToGuess.get(i))) {
                marks.add(Mark.CORRECT);
            } else {
                marks.add(Mark.ABSENT);
            }
        }

        return new Feedback(marks);
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch((mark) -> mark.equals(Mark.CORRECT));
    }

    public List<Mark> getMarks() {
        return marks;
    }
}
