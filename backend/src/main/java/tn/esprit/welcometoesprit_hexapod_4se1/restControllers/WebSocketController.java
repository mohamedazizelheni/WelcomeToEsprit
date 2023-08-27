package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/webSocket")
@SecurityRequirement(name = "bearerAuth")
public class WebSocketController {
    private SimpMessagingTemplate template;
    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template= template;   }
    @MessageMapping("/Send/Message")
    public void onRecivedMessage(String message){
    this.template.convertAndSend("/chat",new SimpleDateFormat("HH:mm:ss").format(new Date())+"- "+message);
    }
}

