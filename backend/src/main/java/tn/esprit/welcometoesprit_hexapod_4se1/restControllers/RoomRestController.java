package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Room;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IRoomService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/room")
@SecurityRequirement(name = "bearerAuth")

public class RoomRestController {
    @Autowired
    IRoomService iRoomService;

    @PostMapping(value = "/createNewRoom", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Room> createNewRoom(@RequestParam String name, @RequestParam String block, @RequestParam MultipartFile file) throws IOException {
        Room room=new Room();
        room.setName(name);
        room.setBlock(block);
        room.setMap(file.getBytes());
        return ResponseEntity.ok(iRoomService.createNewRoom(room));
    }
    @PutMapping(value = "/updateRoom", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Room> updateRoom(@RequestParam int id,@RequestParam(required = false) String name, @RequestParam(required = false) String block, @RequestParam(required = false) MultipartFile file) throws IOException {
        Room room=iRoomService.getRoomById(id);
        if(name!=null)
            room.setName(name);
        if(block!=null)
            room.setBlock(block);
        if(file!=null)
            room.setMap(file.getBytes());
        return ResponseEntity.ok(iRoomService.createNewRoom(room));
    }
    @GetMapping("getRooms")
    public ResponseEntity<List<Room>> getRooms(){return ResponseEntity.ok(iRoomService.getRooms());}
    @GetMapping("getRoomById")
    public ResponseEntity<Room> getRoomById(@RequestParam int id) {return ResponseEntity.ok(iRoomService.getRoomById(id));}
    @DeleteMapping("deleteRoom")
    public ResponseEntity<String> deleteRoom(@RequestParam int id) {
        Room room = iRoomService.getRoomById(id);
        if (room.getInterviews() == null || room.getInterviews().size() == 0) {
            iRoomService.deleteRoom(id);
            return ResponseEntity.ok("Room deleted successfully!");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room can not be deleted, an interview is related to it!");
    }


}
