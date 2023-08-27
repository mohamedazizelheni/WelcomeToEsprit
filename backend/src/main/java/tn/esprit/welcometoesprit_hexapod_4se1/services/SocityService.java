package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Ads;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.FAQ;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Socity;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.SocityRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SocityService {
    @Autowired
    SocityRepository socityRepository ;
    public  void ajoutersocity(Socity socity){
        socityRepository.save(socity);

    }
    public void  modifiersocity(Socity C , int id ){
        Socity SOS  = socityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" FAQ not found with id " + id));
        SOS.setName(C.getName());
        SOS.setEmail(C.getEmail());
        SOS.setURLfacbook(C.getURLfacbook());
        SOS.setURLlinkedin(C.getURLlinkedin());
        SOS.setNumerotelephonique(C.getNumerotelephonique());
        SOS.setLogo(C.getLogo());
        socityRepository.save(SOS);

    }
    public void supprimersocity( int id ){
        socityRepository.deleteById(id);
    }
    public List<Socity> getsocity(){
        return (List<Socity>) socityRepository.findAll();
    }
    public Socity getsocbyid(int id) {
        return   socityRepository.findById(id).get();

    }
}
