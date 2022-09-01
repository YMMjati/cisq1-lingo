package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.data.MarksConverter;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.checkerframework.checker.signature.qual.IdentifierOrPrimitiveType;

import javax.annotation.processing.Generated;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

import static nl.hu.cisq1.lingo.trainer.domain.enums.Mark.CORRECT;

@Entity
public class Feedback {
    @Id
    @GeneratedValue
    private int id;

    @Convert(converter = MarksConverter.class)
    private List<Mark> marks = new ArrayList<>();

    public Feedback(List<Mark> marks) {
        this.marks = marks;
    }

    public Feedback() {
    }

    public static Feedback evaluate(String wordToGuess, String guess) {
        List<Mark> marks = new ArrayList<>();

        if (wordToGuess.length() != guess.length()) {
            marks = Collections.nCopies(wordToGuess.length(), Mark.INVALID);
            return new Feedback(marks);
        }

        List<String> lettersToGuess = Arrays.asList(wordToGuess.split(""));
        List<String> letters = Arrays.asList(guess.split(""));

        for (int i = 0; i < lettersToGuess.size(); i++) {
            if (letters.get(i).equals(lettersToGuess.get(i))) {
                marks.add(CORRECT);
            } else {
                marks.add(Mark.ABSENT);
            }
        }

        return new Feedback(marks);
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch((mark) -> mark.equals(CORRECT));
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public String generateHint(String previousHint, String wordToGuess) {
        String nextHint = "";

        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i).equals(CORRECT)) {
                nextHint += wordToGuess;
            } else {
                nextHint += previousHint.charAt(i);
            }
        }

        return nextHint;
    }
}
