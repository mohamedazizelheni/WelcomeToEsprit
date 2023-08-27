package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Offer;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IOfferService;

import java.util.List;
@RestController
@RequestMapping("api/offer")
@SecurityRequirement(name = "bearerAuth")

public class OfferRestController {
    @Autowired
    IOfferService iOfferService;

    @PostMapping("createNewOffer/admin")
    public ResponseEntity<Offer> createNewOffer(@RequestBody Offer offer){
        return ResponseEntity.ok(iOfferService.createNewOffer(offer));
    }
    @PutMapping("updateOffer/admin")
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer){
        return ResponseEntity.ok(iOfferService.updateOffer(offer));
    }
    @GetMapping("getOfferById/{id}/admin")
    public ResponseEntity<Offer> getOfferById(@PathVariable("id") int id){
        return ResponseEntity.ok(iOfferService.getOfferById(id));
    }
    @GetMapping("getOffers")
    public ResponseEntity<List<Offer>> getOffers(){
        return ResponseEntity.ok(iOfferService.getOffers());
    }

    @DeleteMapping("deleteOffer/{id}/admin")
    public ResponseEntity<String> deleteOffer(@PathVariable("id")int id){
        Offer offer = iOfferService.getOfferById(id);
        if(offer.getOfferCandidacies()==null || offer.getOfferCandidacies().size()==0) {
            iOfferService.deleteOffer(id);
            return ResponseEntity.status(204).body("Offer deleted successfully!");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Offer can not be deleted, a candidacy related to it!");
    }
}
