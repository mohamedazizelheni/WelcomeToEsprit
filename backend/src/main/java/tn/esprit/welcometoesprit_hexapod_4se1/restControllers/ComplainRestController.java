package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Complain;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IComplainService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/complain")
@SecurityRequirement(name = "bearerAuth")

public class ComplainRestController {
    @Autowired
    IComplainService iComplainService;
    @Autowired
    UserService userService;

    @PostMapping("createNewComplain/{interview_id}")
    public ResponseEntity<Complain> createNewComplain(@RequestBody Complain complain, @PathVariable("interview_id") int interview_id, Principal principal){return ResponseEntity.ok(iComplainService.createNewComplain(complain,interview_id,principal));}
    @GetMapping("getComplainById/{id}")
    public ResponseEntity<Complain> getComplainById(@PathVariable("id") int id){return ResponseEntity.ok(iComplainService.getComplainById(id));}
    @GetMapping("getComplainsByIdComplainer/{id}")
    public ResponseEntity<List<Complain>> getComplainsByIdComplainer(@PathVariable("id") int id){return ResponseEntity.ok(iComplainService.getComplainsByIdComplainer(id));}

    @GetMapping("getAllComplains/admin")
    public ResponseEntity<List<Complain>> getAllComplains(){return ResponseEntity.ok(iComplainService.getAllComplains());}
    @PutMapping("updateComplain")
    public ResponseEntity<?> updateComplain(@RequestBody Complain complain){
        int id = complain.getUser().getId();
        // Check if the current user is authorized to delete the specified user
        if (userService.isCurrentUser(id) || SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(iComplainService.updateComplain(complain));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }
    @DeleteMapping("deleteComplain/{id}")
    public ResponseEntity<String> deleteComplain(@PathVariable("id") int id){
        iComplainService.deleteComplain(id);
        return ResponseEntity.ok("Complain deleted successfully");
    }
}
