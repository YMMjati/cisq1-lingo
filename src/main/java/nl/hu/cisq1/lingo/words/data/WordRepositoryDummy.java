package nl.hu.cisq1.lingo.words.data;

import nl.hu.cisq1.lingo.words.domain.Word;
import nl.hu.cisq1.lingo.words.domain.exception.WordLengthNotSupportedException;

import java.util.Random;

// This is a temporary class for generating words without needing Spring or database access; it will be phased out later.
public class WordRepositoryDummy {
    String[] wordList5 = new String[]{
            "photo",
            "thing",
            "month",
            "story",
            "cheek",
            "youth",
            "scene",
            "event",
            "depth",
            "union",
            "queen",
            "salad",
            "night",
            "tooth",
            "video",
            "entry",
            "pizza",
            "basis",
            "apple",
            "river"
    };

    String[] wordList6 = new String[]{
            "region",
            "series",
            "throat",
            "health",
            "county",
            "effort",
            "sample",
            "advice",
            "orange",
            "cousin",
            "tongue",
            "member",
            "family",
            "farmer",
            "speech",
            "driver",
            "client",
            "thanks",
            "safety",
            "sister"
    };

    String[] wordList7 = new String[]{
            "control",
            "village",
            "anxiety",
            "charity",
            "manager",
            "reading",
            "warning",
            "trainer",
            "concept",
            "alcohol",
            "product",
            "student",
            "freedom",
            "chapter",
            "artisan",
            "article",
            "setting",
            "housing",
            "teacher",
            "garbage"
    };

    public Word findRandomWordByLength(int length) {
        int guessIndex = new Random().nextInt(20);

        return switch (length) {
            case 5 -> new Word(this.wordList5[guessIndex]);
            case 6 -> new Word(this.wordList6[guessIndex]);
            case 7 -> new Word(this.wordList7[guessIndex]);
            default -> new Word("invalid");
        };
    }

}
