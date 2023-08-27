package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.FAQRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UpDownRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class FAQService {
    @Autowired
    PostService postService;
    @Autowired
    FAQRepository faqRepository;
    @Autowired
    UserRepository userRepository ;

    @Autowired
    UpDownRepository upDownRepository;
    public void ajouterfaq (FAQ faq , Principal principal){
        java.util.Date curent ;
        curent = new java.util.Date(System.currentTimeMillis());
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);
        faq.setUser(user);
        faq.setAdddate(curent);
        faqRepository.save(faq);
    }
    public FAQ getfaq (int id){
       return   faqRepository.findById(id).get();
    }
    public void supprimerfaq(int id ){
        faqRepository.deleteById(id);
    }
    public void modifierfaq(FAQ faq , int id ){
        FAQ F  = faqRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" FAQ not found with id " + id));
        F.setAnswer(faq.getAnswer());
        F.setQuestion(faq.getQuestion());
        F.setSubject(faq.getSubject());

        faqRepository.save(F);
    }
    public List<FAQ> getallfaq(){
        return (List<FAQ>) faqRepository.findAll();
    }
    public  void MakeUp(int idAds ,Principal principal){
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);
        UpDownId upDownId = new UpDownId();
        UpDown upDown = new UpDown();
        upDownId.setUser(user);
        upDownId.setFaq(faqRepository.findById(idAds).orElse(null));
        upDown.setUpDownId(upDownId);
        upDown.setValue(1);
        upDownRepository.save(upDown);
    }
    public  void MakeDown(int idAds , Principal principal){
        UpDownId upDownId = new UpDownId();
        UpDown upDown = new UpDown();
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);
        upDownId.setUser(user);
        upDownId.setFaq(faqRepository.findById(idAds).orElse(null));
        upDown.setUpDownId(upDownId);
        upDown.setValue(2);
        upDownRepository.save(upDown);
    }

    public int getNbUp(int p) {
        FAQ faq = faqRepository.findById(p).get();
        if(faqRepository.countnbUpbypost(faq)!=0)
            return  faqRepository.countnbUpbypost(faq);
        return 0;
    }
    public int getNbDesDown(int p) {
        FAQ faq = faqRepository.findById(p).get();
        if(faqRepository.countnbdesDownbypost(faq)!=0)
            return faqRepository.countnbdesDownbypost(faq);
        return 0;
    }
   public List<categorieFAQ> getAllCategories() {
        return Arrays.asList(categorieFAQ.values());
    }



}
