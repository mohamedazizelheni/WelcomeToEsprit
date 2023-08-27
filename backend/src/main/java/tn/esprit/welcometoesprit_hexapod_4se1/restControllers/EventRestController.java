package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Event;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Offer;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IEventService;

import java.io.IOException;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/event")
@SecurityRequirement(name = "bearerAuth")
public class EventRestController {
    @Autowired
    IEventService iEventService;
    @PostMapping(value="ajouterEvent",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,"planning/*"})
    Event ajouterEvent(@RequestParam String name,@RequestParam String space, @RequestParam Date date ,@RequestParam MultipartFile planning) throws IOException {
        Event event = new Event();

        event.setName(name);
        event.setSpace(space);
        event.setDate(date);
        event.setPlanning(planning.getBytes());
        return iEventService.ajouterEvent(event);
    }
    @GetMapping("getEvents")
    public List<Event> getEvents(){return iEventService.getEvents();}
    @PutMapping("/Update")
    public Event modifierevent(@RequestBody Event updateEvent){

        return iEventService.updateEvent(updateEvent);
    }
    @GetMapping("getEventById/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") int id){
        return ResponseEntity.ok(iEventService.getEventById(id));
    }

    @GetMapping("/rechercheEvent")
    public List<Event> rechercheEvent(@RequestParam Date date){
        List<Event> events=iEventService.rechercheEvent(date);
        return events;
    }

    @DeleteMapping ("/Delete")
    public Event deleteEvent(@RequestParam int event_id){
        iEventService.deleteById(event_id);
        return null;
    }
}
