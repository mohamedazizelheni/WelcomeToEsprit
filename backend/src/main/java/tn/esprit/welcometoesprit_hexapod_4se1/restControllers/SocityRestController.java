package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Socity;
import tn.esprit.welcometoesprit_hexapod_4se1.services.SocityService;

import java.util.List;

@RestController
@RequestMapping("api/socity")
@SecurityRequirement(name = "bearerAuth")
public class SocityRestController {
    @Autowired
    SocityService  socityService ;
    @PostMapping("/add")
    public void addsocity(@RequestBody Socity socity){
        socityService.ajoutersocity(socity);
    }
    @PutMapping("update/{id}")
    public void updatesocity(@RequestBody Socity socity ,@PathVariable("id") int id  ){
        socityService.modifiersocity(socity,id);
    }
    @DeleteMapping ("delete/{id}")
    public void deletesocity( @PathVariable("id") int id ){
        socityService.supprimersocity(id);
    }
    @GetMapping("getall")
    public List<Socity> getall(){
         return socityService.getsocity();
    }
    @GetMapping("getsocitybyid/{id}")
    public Socity getsocity(@PathVariable("id") int id){
        return socityService.getsocbyid(id);

}}
