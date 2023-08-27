package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Event;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Question;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IQuestionService;

import java.util.List;

@RestController
@RequestMapping("api/question")
@SecurityRequirement(name = "bearerAuth")
public class QuestionRestController {
    @Autowired
    IQuestionService iQuestionService;
    @PostMapping("createNewQuestion")
    public Question createNewQuestion(@RequestBody Question question){return iQuestionService.createNewQuestion(question);}

    @PostMapping("/getresultquestion")
    public String getResultquest(@RequestParam String reponse,@RequestParam int id){
        return iQuestionService.getResultQuestion(reponse,id);
    }
    @DeleteMapping("deletequestion/{id}")
    public void removeQuESTIONById(@PathVariable("id")int id){ iQuestionService.removeQuestionById(id); }
@GetMapping("getQuestion")
    public List<Question>getQuestion(){return iQuestionService.getQuestion();}
    @GetMapping("getQuestionById/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") int id){
        return ResponseEntity.ok(iQuestionService.getQuestionById(id));
    }

}
