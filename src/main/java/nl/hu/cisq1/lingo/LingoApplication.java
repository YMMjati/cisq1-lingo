package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.WordRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class LingoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LingoApplication.class, args);


    }
}
