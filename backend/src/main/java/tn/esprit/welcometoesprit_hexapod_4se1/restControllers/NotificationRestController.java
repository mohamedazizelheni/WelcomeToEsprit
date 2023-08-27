package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.welcometoesprit_hexapod_4se1.services.InotificationService;

@RestController
@RequestMapping("api/notification")
@SecurityRequirement(name = "bearerAuth")
public class NotificationRestController {
    @Autowired
    InotificationService inotificationService;

}
