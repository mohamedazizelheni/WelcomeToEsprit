package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Interview;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IInterviewService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/interview")
@SecurityRequirement(name = "bearerAuth")

public class InterviewRestController {
    @Autowired
    IInterviewService iInterviewService;

    @GetMapping("/getDays")
    public ResponseEntity<List<String>> getDays(@RequestParam int k) {
        return ResponseEntity.ok(iInterviewService.getDays(k));
    }

    @GetMapping("/getHours")
    public ResponseEntity<List<String>> gethours(@RequestParam String date, @RequestParam int k) throws ParseException {
        return ResponseEntity.ok(iInterviewService.getHours(date, k));
    }

    @PutMapping("updateInterview/admin")
    public ResponseEntity<Interview> updateInterview(@RequestParam int interview_id,@RequestParam String day, @RequestParam String hour, @RequestParam int k) throws ParseException {
        return ResponseEntity.ok(iInterviewService.updateInterview(interview_id, day, hour, k));
    }

    @GetMapping("/getInterviewsByUserId/{id}")
    public ResponseEntity<List<Interview>> getInterviewsByUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(iInterviewService.getInterviewsByUserId(id));
    }

    @GetMapping("getInterviewById/{id}")
    public ResponseEntity<Interview> getInterviewById(@PathVariable("id") int id) {
        Interview interview = iInterviewService.getInterviewById(id);
        if (interview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(interview);
    }


    @GetMapping("getInterviewByAdmissionCandidacyId/{id}")
    public ResponseEntity<Interview> getInterviewByAdmissionCandidacyId(@PathVariable("id") int id) {
        Interview interview = iInterviewService.getInterviewByAdmissionCandidacyId(id);
        if (interview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(interview);
    }


    @GetMapping("getInterviewByOfferCandidacyId/{id}")
    public ResponseEntity<Interview> getInterviewByOfferCandidacyId(@PathVariable("id") int id) {
        Interview interview = iInterviewService.getInterviewByOfferCandidacyId(id);
        if (interview == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(interview);
    }
}
