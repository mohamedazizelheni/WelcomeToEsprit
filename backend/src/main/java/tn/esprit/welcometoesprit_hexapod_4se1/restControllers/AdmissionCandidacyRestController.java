package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.ResourceNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import tn.esprit.welcometoesprit_hexapod_4se1.services.*;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/admission")
@SecurityRequirement(name = "bearerAuth")
public class AdmissionCandidacyRestController {
    @Autowired
    IAdmissionCandidacyService iAdmissionCandidacyService;
    @Autowired
    IInterviewService iInterviewService;
    @Autowired
    AdmissionCandidacyService admissionCandidacyService;
    @Autowired
    InterviewService interviewService;
    @Autowired
    UserService userService;

    @PostMapping(value = "createNewAdmissionCandidacy", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AdmissionCandidacy createNewAdmissionCandidacy(@RequestParam (required = false) Boolean bac,
                                                          @RequestParam(required = false) MultipartFile docBacDiploma,
                                                          @RequestParam(required = false) Float bacMoy,
                                                          @RequestParam(required = false) MultipartFile docBacReleve,
                                                          @RequestParam String bacYear,
                                                          @RequestParam String bacEstablishment,
                                                          @RequestParam String bacGovernorate,
                                                          @RequestParam(required = false) Float moy1,
                                                          @RequestParam(required = false) MultipartFile docReleve1,
                                                          @RequestParam(required = false) Float moy2,
                                                          @RequestParam(required = false) MultipartFile docReleve2,
                                                          @RequestParam(required = false) Float moy3,
                                                          @RequestParam(required = false) MultipartFile docReleve3,
                                                          @RequestParam(required = false) Float moy4,
                                                          @RequestParam(required = false) MultipartFile docReleve4,
                                                          @RequestParam(required = false) Boolean diploma,
                                                          @RequestParam(required = false) MultipartFile docDiploma,
                                                          @RequestParam String level,
                                                          @RequestParam String speciality,
                                                          @RequestParam String day,
                                                          @RequestParam String hour, Principal principal) throws IOException, ParseException {
        AdmissionCandidacy admissionCandidacy = new AdmissionCandidacy();
        admissionCandidacy.setBacYear(bacYear);
        admissionCandidacy.setBacEstablishment(bacEstablishment);
        admissionCandidacy.setBacGovernorate(bacGovernorate);
        if (bac) {
            admissionCandidacy.setBac(true);
            admissionCandidacy.setDocBacDiploma(docBacDiploma.getBytes());
            admissionCandidacy.setBacMoy(bacMoy);
            admissionCandidacy.setDocBacReleve(docBacReleve.getBytes());
            if (moy1 != null) {
                admissionCandidacy.setMoy1(moy1);
                admissionCandidacy.setDocReleve1(docReleve1.getBytes());
                if (moy2 != null) {
                    admissionCandidacy.setMoy2(moy2);
                    admissionCandidacy.setDocReleve2(docReleve2.getBytes());
                    if (moy3 != null) {
                        admissionCandidacy.setMoy3(moy3);
                        admissionCandidacy.setDocReleve3(docReleve3.getBytes());
                        if (moy4 != null) {
                            admissionCandidacy.setMoy4(moy4);
                            admissionCandidacy.setDocReleve4(docReleve4.getBytes());
                            if (diploma != null && diploma) {
                                admissionCandidacy.setDiploma(true);
                                admissionCandidacy.setDocDiploma(docDiploma.getBytes());
                            }
                            else admissionCandidacy.setDiploma(false);
                        }
                    }
                }
            }
        }
        else admissionCandidacy.setBac(false);
        admissionCandidacy.setLevel(level);
        admissionCandidacy.setSpeciality(speciality);
        Interview interview = iInterviewService.createNewInterview(day, hour, 2);
        admissionCandidacy.setInterview(interview);
        return iAdmissionCandidacyService.createNewAdmissionCandidacy(admissionCandidacy,principal);
    }

    @GetMapping("getAdmissionCandidacyById/{id}")
    public AdmissionCandidacy getAdmissionCandidacyById(@PathVariable int id) {
        return iAdmissionCandidacyService.getAdmissionCandidacyById(id);
    }

    @PutMapping(value = "updateAdmissionCandidacy/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AdmissionCandidacy updateAdmissionCandidacy(@PathVariable int id,
                                                       @RequestParam(required = false) Boolean bac,
                                                       @RequestParam(required = false) MultipartFile docBacDiploma,
                                                       @RequestParam(required = false) Float bacMoy,
                                                       @RequestParam(required = false) MultipartFile docBacReleve,
                                                       @RequestParam(required = false) String bacYear,
                                                       @RequestParam(required = false) String bacEstablishment,
                                                       @RequestParam(required = false) String bacGovernorate,
                                                       @RequestParam(required = false) Float moy1,
                                                       @RequestParam(required = false) MultipartFile docReleve1,
                                                       @RequestParam(required = false) Float moy2,
                                                       @RequestParam(required = false) MultipartFile docReleve2,
                                                       @RequestParam(required = false) Float moy3,
                                                       @RequestParam(required = false) MultipartFile docReleve3,
                                                       @RequestParam(required = false) Float moy4,
                                                       @RequestParam(required = false) MultipartFile docReleve4,
                                                       @RequestParam(required = false) Boolean diploma,
                                                       @RequestParam(required = false) MultipartFile docDiploma,
                                                       @RequestParam(required = false) String level,
                                                       @RequestParam(required = false) String speciality) throws IOException, ParseException {
        AdmissionCandidacy admissionCandidacy = iAdmissionCandidacyService.getAdmissionCandidacyById(id);
        if (bac != null) {
            admissionCandidacy.setBac(bac);
        }
        if (docBacDiploma != null) {
            admissionCandidacy.setDocBacDiploma(docBacDiploma.getBytes());
        }
        if (bacMoy != null) {
            admissionCandidacy.setBacMoy(bacMoy);
        }
        if (docBacReleve != null) {
            admissionCandidacy.setDocBacReleve(docBacReleve.getBytes());
        }
        if (bacYear != null) {
            admissionCandidacy.setBacYear(bacYear);
        }
        if (bacEstablishment != null) {
            admissionCandidacy.setBacEstablishment(bacEstablishment);
        }
        if (bacGovernorate != null) {
            admissionCandidacy.setBacGovernorate(bacGovernorate);
        }
        if (moy1 != null) {
            admissionCandidacy.setMoy1(moy1);
        }
        if (docReleve1 != null) {
            admissionCandidacy.setDocReleve1(docReleve1.getBytes());
        }
        if (moy2 != null) {
            admissionCandidacy.setMoy2(moy2);
        }
        if (docReleve2 != null) {
            admissionCandidacy.setDocReleve2(docReleve2.getBytes());
        }

        if (moy3 != null) {
            admissionCandidacy.setMoy3(moy3);
        }
        if (docReleve3 != null) {
            admissionCandidacy.setDocReleve3(docReleve3.getBytes());
        }
        if (moy4 != null) {
            admissionCandidacy.setMoy4(moy4);
        }
        if (docReleve4 != null) {
            admissionCandidacy.setDocReleve4(docReleve4.getBytes());
        }
        if (diploma != null) {
            admissionCandidacy.setDiploma(diploma);
        }
        if (docDiploma != null) {
            admissionCandidacy.setDocDiploma(docDiploma.getBytes());
        }
        if (level != null) {
            admissionCandidacy.setLevel(level);
        }
        if (speciality != null) {
            admissionCandidacy.setSpeciality(speciality);
        }
        return iAdmissionCandidacyService.updateAdmissionCandidacy(admissionCandidacy);
    }
    @GetMapping("getAdmissionCandidacy")
    public List<AdmissionCandidacy> getAdmissionCandidacy(){return iAdmissionCandidacyService.getAdmissionCandidacy();}

        @GetMapping("/average-test-score")
        public ResponseEntity<Float> getAverageTestScore() {
            float averageScore = admissionCandidacyService.calculateAverageTestScore();
            return ResponseEntity.ok(averageScore);
        }
    @GetMapping("/speciality-test-average")
    public Map<String, Double> getSpecialityTestAverages() {
        return admissionCandidacyService.getSpecialityTestAverages();
    }
    @DeleteMapping("delete-candidature/{id}")
    public void removeAdmissionCandidacy(@PathVariable("id")int id){ iAdmissionCandidacyService.removeAdmissionCandidacy(id);

    }
    @GetMapping("getAdmissionCandidacyByIdCandidate/{id}")
    public ResponseEntity<AdmissionCandidacy> getAdmissionCandidacyByIdCandidate(@PathVariable("id") int id){
        return ResponseEntity.ok(iAdmissionCandidacyService.getAdmissionCandidacyByIdCondidate(id));
    }

    @PutMapping("updateAdmissionCandidacyStatus")
    public ResponseEntity<AdmissionCandidacy> updateAdmissionCandidacyStatus(@RequestParam int id, @RequestParam String status){
        return ResponseEntity.ok(iAdmissionCandidacyService.updateAdmissionCandidacyStatus(id,status));
    }
    @PutMapping("updateAdmissionCandidacyScore")
    public ResponseEntity<?> updateAdmissionCandidacyScore(@RequestParam int id, @RequestParam Float score){
        if (userService.isCurrentUser(interviewService.getInterviewByAdmissionCandidacyId(id).getUser().getId())) {
            return ResponseEntity.ok(iAdmissionCandidacyService.updateAdmissionCandidacyScore(id,score));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

}

