package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Invitation;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IInvitationService;

import java.util.List;

@RestController
@RequestMapping("api/invitation")
@SecurityRequirement(name = "bearerAuth")
public class InvitationRestController {
    @Autowired
    IInvitationService iInvitationService;

    @PostMapping("inviterSpeaker")
    public Invitation inviterSpeaker(@RequestParam int even_id,@RequestParam int user_id){
        return iInvitationService.inviterSpeaker(even_id,user_id);
    }

    @GetMapping("getUserSpeakers")
    public List<User> getUserSpeakers(){return iInvitationService.getUserSpeakers();}
    @GetMapping("getUsersStudents")
    public List<User> getUsersStudents(){return iInvitationService.getUsersStudents();}

    @GetMapping("inviterStudents")
    public void inviterStudents(@RequestParam int event_id,@RequestParam String niveau){
        iInvitationService.inviterStudents(event_id, niveau);}
    @GetMapping("getInvitations")
    public List<Invitation> getInvitations(@RequestParam String name){
        return iInvitationService.getInvitations(name);
    }
    @PutMapping("presence")
    public void updatePresence(@RequestParam int id, @RequestParam boolean present) {
        iInvitationService.updatePresence(id, present);
    }

}


