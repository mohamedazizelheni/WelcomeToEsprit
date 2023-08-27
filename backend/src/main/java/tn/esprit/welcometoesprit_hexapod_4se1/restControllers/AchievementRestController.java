package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Achievement;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Event;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Invitation;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Reaction;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AchievementRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.EventRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IAchievementService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/achievement")
@SecurityRequirement(name = "bearerAuth")
public class AchievementRestController {
    @Autowired
    IAchievementService iAchievementService;
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    EventRepository eventRepository;
    @PostMapping (value = "addAchievement", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,"video/*"})
    public Achievement addAchievementandAffecteEvent(@RequestParam int id,@RequestParam String name,@RequestParam MultipartFile video) throws IOException {
        Event event = eventRepository.findById(id).get();
        // create a new achievement and assign it to the event
        Achievement achievement = new Achievement();
        achievement.setName(name);
        achievement.setEvent(event);
        achievement.setVideo(video.getBytes());
        achievement.setArchived(false);

        return iAchievementService.addAchievementandAffecteEvent(achievement);
    }
    @GetMapping("getAllAchievement")
    public List<Achievement> getAllAchievement(){return iAchievementService.getAllAchievement();}
    @PutMapping("archived")
    public ResponseEntity<String> archiveAchievement(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        iAchievementService.archiveAchievement(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getAchievement")
    public List<Achievement> getAchievement(){
        return iAchievementService.getAchievement();
    }
    @GetMapping("getAchievementArchived")
    public List<Achievement> getAchievementArchived(){
        return iAchievementService.getAchievementArchived();
    }
    @PutMapping(value = "update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,"video/*"})
    public ResponseEntity<Achievement> updateAchievement(@PathVariable(value = "id") int achievementId,@RequestParam String nameUpdate, @RequestParam("video") MultipartFile video) throws IOException {
        Achievement updatedAchievement = iAchievementService.updateAchievement(achievementId,nameUpdate, video);
        updatedAchievement.setName(updatedAchievement.getName());
        return ResponseEntity.ok(updatedAchievement);
    }
    @GetMapping("getAchievementById/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable("id") int id){
        return ResponseEntity.ok(iAchievementService.getAchievementById(id));
    }

    @GetMapping("findAchievemntsByEventId/{id}")
    public ResponseEntity<List<Achievement>> findAchievemntsByEventId(@PathVariable("id") int id){
        return ResponseEntity.ok(iAchievementService.findAchievemntsByEventId(id));
    }
}
