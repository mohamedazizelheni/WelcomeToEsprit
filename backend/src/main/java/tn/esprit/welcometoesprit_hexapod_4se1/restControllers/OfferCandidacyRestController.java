package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Interview;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Offer;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.OfferCandidacy;
import tn.esprit.welcometoesprit_hexapod_4se1.services.*;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/offerCandidacy")
@SecurityRequirement(name = "bearerAuth")

public class OfferCandidacyRestController {
    @Autowired
    IOfferCandidacyService iOfferCandidacyService;
    @Autowired
    IInterviewService iInterviewService;
    @Autowired
    IOfferService iOfferService;
    @Autowired
    InterviewService interviewService;
    @Autowired
    UserService userService;

    @PostMapping(value = "createNewOfferCandidacy", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<OfferCandidacy> createNewOfferCandidacy(@RequestParam int offer_id, @RequestParam String establishment, @RequestParam MultipartFile docDiploma, @RequestParam MultipartFile docCV, @RequestParam MultipartFile docLetter, @RequestParam String day, @RequestParam String hour, Principal principal) throws IOException, ParseException {
        OfferCandidacy offerCandidacy=new OfferCandidacy();
        offerCandidacy.setEstablishment(establishment);
        offerCandidacy.setDocDiploma(docDiploma.getBytes());
        offerCandidacy.setDocCV(docCV.getBytes());
        offerCandidacy.setDocLetter(docLetter.getBytes());
        Interview interview=iInterviewService.createNewInterview(day,hour,1);
        offerCandidacy.setInterview(interview);
        Offer offer = iOfferService.getOfferById(offer_id);
        offerCandidacy.setOffer(offer);
        return ResponseEntity.ok(iOfferCandidacyService.createNewOfferCandidacy(offerCandidacy,principal));
    }
    @GetMapping("getOfferCandidacyById/{id}")
    public ResponseEntity<OfferCandidacy> getOfferCandidacyById(@PathVariable("id") int id){
        return ResponseEntity.ok(iOfferCandidacyService.getOfferCandidacyById(id));}
    @GetMapping("getOfferCandidaciesByOfferId/{id}")
    public ResponseEntity<List<OfferCandidacy>> getOfferCandidaciesByIdOffer(@PathVariable("id") int id){
        return ResponseEntity.ok(iOfferCandidacyService.getOfferCandidaciesByIdOffer(id));}
    @GetMapping("getOfferCandidaciesByIdCandidate/{id}")
    public ResponseEntity<List<OfferCandidacy>> getOfferCandidaciesByIdCandidate(@PathVariable("id") int id){
        return ResponseEntity.ok(iOfferCandidacyService.getOfferCandidaciesByIdCondidate(id));
    }
    @GetMapping("getOfferCandidaciesByOfferIdAndUserId/{offerId}/{userId}")
    public ResponseEntity<OfferCandidacy> getOfferCandidaciesByOfferIdAndUserId(@PathVariable("offerId") int offerId, @PathVariable("userId") int userId){
        return ResponseEntity.ok(iOfferCandidacyService.getOfferCandidaciesByOfferIdAndUserId(offerId,userId));
    }
    @PutMapping(value = "updateOfferCandidacy/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<OfferCandidacy> updateOfferCandidacy(@PathVariable("id") int id, @RequestParam (required = false) String establishment, @RequestParam (required = false) MultipartFile docDiploma, @RequestParam (required = false) MultipartFile docCV, @RequestParam (required = false) MultipartFile docLetter, @RequestParam (required = false) Float score, @RequestParam (required = false) String status) throws IOException, ParseException {
        OfferCandidacy offerCandidacy=iOfferCandidacyService.getOfferCandidacyById(id);
        if (!(establishment==null))
            offerCandidacy.setEstablishment(establishment);
        if(!(docDiploma==null))
            offerCandidacy.setDocDiploma(docDiploma.getBytes());
        if(!(docCV==null))
            offerCandidacy.setDocCV(docCV.getBytes());
        if(!(docLetter==null))
            offerCandidacy.setDocLetter(docLetter.getBytes());
        if(!(score==null))
            offerCandidacy.setScore(score);
        if(!(status==null))
            offerCandidacy.setStatus(status);
        return ResponseEntity.ok(iOfferCandidacyService.updateOfferCandidacy(offerCandidacy));
    }

    @DeleteMapping("deleteOfferCandidacyById/{id}")
    public ResponseEntity<String> deleteOfferCandidacyById(@PathVariable("id") int id) {
        Interview interview = iOfferCandidacyService.getOfferCandidacyById(id).getInterview();
        if (interview.getComplain() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Offer candidacy cannot be deleted, a complain is related to its interview!");
        else {
            iOfferCandidacyService.deleteOfferCandidacyById(id);
            iInterviewService.deleteInterviewById(interview.getId());
            return ResponseEntity.ok("Offer candidacy deleted successfully");
        }
    }
    @DeleteMapping("deleteOfferCandidacies/{id_offer}")
    public ResponseEntity<String> deleteOfferCandidacies(@PathVariable("id_offer") int offer_id){
        int i = iOfferCandidacyService.deleteOfferCandidaciesByOfferId(offer_id);
        if(i==-1)
            return ResponseEntity.ok("Nothing to delete, the list of candidacies for this offer is empty!");
        else if(i==1)
            return ResponseEntity.ok(i+" candidacy not deleted, complain related to its interview!");
        else if (i>1)
            return ResponseEntity.ok(i+" candidacies not deleted, complains related to their interviews!");
        else
            return ResponseEntity.ok("Candidacies successfully deleted!");
    }
    @GetMapping("getOfferCandidacyStatisticsByDate/admin")
    public ResponseEntity<Map<Date, Integer>> getOfferCandidacyStatisticsByDate(@RequestParam int id){return ResponseEntity.ok(iOfferCandidacyService.getOfferCandidacyStatisticsByDate(id));}
    @PutMapping("updateOfferCandidacyStatus")
    public ResponseEntity<OfferCandidacy> updateOfferCandidacyStatus(@RequestParam int id, @RequestParam String status){
        return ResponseEntity.ok(iOfferCandidacyService.updateOfferCandidacyStatus(id,status));
    }
    @PutMapping("updateOfferCandidacyScore")
    public ResponseEntity<?> updateOfferCandidacyScore(@RequestParam int id, @RequestParam Float score){
        if (userService.isCurrentUser(interviewService.getInterviewByOfferCandidacyId(id).getUser().getId())) {
            return ResponseEntity.ok(iOfferCandidacyService.updateOfferCandidacyScore(id,score));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }
}
