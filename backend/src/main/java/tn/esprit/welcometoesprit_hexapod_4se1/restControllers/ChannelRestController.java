package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Channel;
import tn.esprit.welcometoesprit_hexapod_4se1.services.ChannelService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IChannelService;

@RestController
@RequestMapping("api/channel")
@SecurityRequirement(name = "bearerAuth")
public class ChannelRestController {
    @Autowired
    ChannelService channelService ;
    @PostMapping("{idsos}")
    public void addch( @RequestBody Channel channel,   @PathVariable("idsos") int id ){
        channelService.ajouterch(channel ,id);
    }
    @PutMapping
    public void update( @RequestBody Channel channel, @PathVariable("id") int id ){
        channelService.modifierch(channel,id);
    }
    @DeleteMapping
    public void deletech( @PathVariable("id") int id){
        channelService.supprimerch(id);

    }
}
