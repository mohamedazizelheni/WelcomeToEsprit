package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.AdmissionCandidacy;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Question;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Test;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdmissionCandidacyRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.services.AdmissionCandidacyService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.ITestService;

import java.util.List;

@RestController
@RequestMapping("api/test")
@SecurityRequirement(name = "bearerAuth")
public class TestRestController {
    @Autowired
    ITestService iTestService;
    @Autowired
    AdmissionCandidacyService admissionCandidacyService;
    @Autowired
    AdmissionCandidacyRepository admissionCandidacyRepository;

    @PostMapping("/createNewTest")
    public Test createNewTest(@RequestBody Test test){return iTestService.createNewTest(test);}
    @GetMapping("getTestById/{id}")
    public ResponseEntity<Test> getQuestionById(@PathVariable("id") int id){
        return ResponseEntity.ok(iTestService.getTestById(id));
    }
    @PutMapping  ("updateTest")
    public Test addOrUpdate(@RequestBody Test test ){return iTestService.addOrUpdate(test);}
    @DeleteMapping("removeTestById/{id}")
    public void removeTestById(@PathVariable("id")int id){ iTestService.removeTestById(id); }

    @GetMapping("getResultTest")
    public String getResultTest(@RequestParam int testId, @RequestParam int candidacyId,
                                @RequestParam String rep, @RequestParam String rep1,
                                @RequestParam String rep2, @RequestParam String rep3) {
        String result = iTestService.getresultTest(testId, candidacyId, rep, rep1, rep2, rep3);
        AdmissionCandidacy candidacy = admissionCandidacyRepository.findById(candidacyId).orElseThrow(() -> new NotFoundException("Candidacy not found"));
//        candidacy.setTestScore(Float.parseFloat(result.split(":")[1].trim()));
        candidacy.setTestScore(Float.parseFloat(result));

        admissionCandidacyRepository.save(candidacy);
        return result;
    }
    @GetMapping ("getTest")
    public List<Test> getTest(){return iTestService.getTest();}
    }

