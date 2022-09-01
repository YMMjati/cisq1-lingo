package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.TrainerService;
import nl.hu.cisq1.lingo.trainer.domain.SessionReport;
import org.springframework.web.bind.annotation.*;

// TODO let op juiste HTTP statuscodes en richt je bij URLs op resources en niet op acties
@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @PostMapping("/session")
    public SessionReport createNewSession() {
        return this.service.startSession();
    }

    @PostMapping("/session/{sessionId}/guess")
    public SessionReport guessWord(@PathVariable int sessionId, @RequestBody GuessDto guessDto) {
        return this.service.guessWord(sessionId, guessDto.attempt);
    }

    @GetMapping("/session/{sessionId}")
    public SessionReport getSession(@PathVariable int sessionId) {
        return this.service.getSessionReport(sessionId);
    }
}
