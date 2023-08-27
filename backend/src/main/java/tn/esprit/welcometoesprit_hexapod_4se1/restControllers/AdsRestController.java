package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Ads;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Categorieads;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Postetheme;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdsRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.services.AdsService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/ads")
@SecurityRequirement(name = "bearerAuth")
public class AdsRestController {
    @Autowired
    AdsService adsService ;
    @PostMapping("/add")
    public void addads(@RequestBody Ads ads, Principal principal){

        adsService.ajouteradsswithpromo(ads,principal);
    }

    @PutMapping("update/{idads}")
    public void updateads( @RequestBody Ads ads,@PathVariable("idads") int id ){
        adsService.modifierads(ads,id);
    }
    @DeleteMapping("/{id}")
    public  void deleteads(@PathVariable("id")   int id  ){
        adsService.supprimerads(id);
    }
    @GetMapping("getads/{id}")
    public Ads getadss( @PathVariable("id") int id){
        return  adsService.getAd(id) ;
    }
    @GetMapping("allads")
    public List<Ads> getads(){
        return adsService.recupererlistads();
    }
   /* @PostMapping("/ajout/{id}")
    public void Ajouteradsss(@RequestBody Ads ads ,  @PathVariable("id") int id ){
        adsService.ajouteradss(ads ,id);
    }*/

    @GetMapping("statmonth/{mois}")
    public  float getstatmonth( @PathVariable("mois") int mois){
        return   adsService.getstatadswithonemonth(mois);
    }

    /*   @GetMapping("statmonthall")
       public Map<String, Float> getstatadsbymonthall(){
           return adsService.getstatfromcategorie();
       }*/
   /* @GetMapping("aze/{id}")
    public String statadsfrommonth(@PathVariable("id") int id , @RequestBody Categorieads C){
        return    adsService.getstatfromcategorie(id, C);

    }*/
    @PutMapping("NBvu/{id}")
    public void increnbvu(@PathVariable("id") int id){
        adsService.increNBvu(id );
    }

}
