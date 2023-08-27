package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.FAQ;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.categorieFAQ;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.FAQRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.services.FAQService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IFAQService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.pdfConfigService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/faq")
@SecurityRequirement(name = "bearerAuth")

public class FAQRestController {
    @Autowired
    FAQService faqService ;
    @Autowired
    private FAQRepository fAQRepository;
    @Autowired
    tn.esprit.welcometoesprit_hexapod_4se1.services.pdfConfigService pdfConfigService;

    @PostMapping("add")
    public  void addfaq (@RequestBody FAQ faq , Principal principal){
        faqService.ajouterfaq(faq,principal);
    }

    @PutMapping({"update/{id}"})
    public void updatefaq(@RequestBody FAQ  faq , @PathVariable("id") int id){
        faqService.modifierfaq(faq,id);
    }
    @DeleteMapping("delete/{id}")
    public void deletefaq(  @PathVariable("id") int id ){
        faqService.supprimerfaq(id);
    }
    @GetMapping ("getfaq/{id}")
    public FAQ getfaqq( @PathVariable("id")  int id ){
        return faqService.getfaq(id);
    }

    @GetMapping("allfaq")
    public List<FAQ> listfaq(){
        return (List<FAQ>) fAQRepository.findAll();
    }
    @PostMapping("/upfaq/{idfaq}")
    public void makeUp(@PathVariable("idfaq") int idfaq ,Principal principal){
        faqService.MakeUp(idfaq,principal);
    }
    @PostMapping("/Downfaq/{idfaq}")
    public void makeDown(@PathVariable("idfaq") int idfaq ,Principal principal ){
        faqService.MakeDown(idfaq,principal);
    }
    @GetMapping("/print/{name}")
    public void printpdf( @PathVariable("name") String  name) throws JRException, DRException, IOException {
         pdfConfigService.print(name);
    }
    @GetMapping("/getupfaq/{idFaq}")
    public  Integer getNbUp(@PathVariable("idFaq") int idFaq ){
        return faqService.getNbUp(idFaq);
    }
    @GetMapping("/getDownfaq/{idFaq}")
    public  Integer getNbDown(@PathVariable("idFaq") int idFaq ){
        return faqService.getNbDesDown(idFaq);
    }
    @GetMapping("getallsubject")
    public List<categorieFAQ>  getallsub (){
        return faqService.getAllCategories();
    }
}
