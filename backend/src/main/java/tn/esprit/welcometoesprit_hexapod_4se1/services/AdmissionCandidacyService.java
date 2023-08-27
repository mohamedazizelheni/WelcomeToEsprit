package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdmissionCandidacyRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.TestRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.security.Principal;
import java.util.*;

@Service
public class AdmissionCandidacyService implements IAdmissionCandidacyService {
    @Autowired
    AdmissionCandidacyRepository admissionCandidacyRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<AdmissionCandidacy> getAdmissionCandidacy(){
        List<AdmissionCandidacy> admissionCandidacies=admissionCandidacyRepository.findAll();
        return admissionCandidacies;
    }

   @Override
    public AdmissionCandidacy createNewAdmissionCandidacy(AdmissionCandidacy admissionCandidacy, Principal principal){
        List<Test> tests1= (List<Test>) testRepository.findAllByType("generalCulture");
        List<Test> tests2= (List<Test>) testRepository.findAllByType("languageSkills");
        Random random=new Random();
        admissionCandidacy.setTests(new ArrayList<Test>());
        if(tests1.size()>0) {
            int i = random.nextInt(tests1.size());
            Test test1 = tests1.get(i);
            admissionCandidacy.getTests().add(test1);
        }
        if(tests2.size()>0) {
            int j = random.nextInt(tests2.size());
            Test test2 = tests2.get(j);
            admissionCandidacy.getTests().add(test2);
        }
        Date date=new Date();
       User user = userRepository.findUserByMail(principal.getName()).orElse(null);
//        User user= userRepository.findById(2).get();
        admissionCandidacy.setUser(user);
        admissionCandidacy.setCreationDate(date);
        admissionCandidacy.setStatus("waiting for interview");
        return admissionCandidacyRepository.save(admissionCandidacy);

    }
   @Override
    public AdmissionCandidacy getAdmissionCandidacyById(int id){
            return admissionCandidacyRepository.findById(id).orElse(null);
    }
    @Override
    public AdmissionCandidacy updateAdmissionCandidacy(AdmissionCandidacy admissionCandidacy){
        return admissionCandidacyRepository.save(admissionCandidacy);
    }
     @Override
    public void  removeAdmissionCandidacy(int id){
       admissionCandidacyRepository.deleteById(id);
    }

    @Override

    public void updateTestScore(int candidacyId, int testId, float testScore) {
        AdmissionCandidacy candidacy = admissionCandidacyRepository.findById(candidacyId)
                .orElse(null);
        if (candidacy == null) {
            // gérer le cas où la candidature n'existe pas
            return;
        }
        List<Test> tests = candidacy.getTests();
        Test test = null;
        for (Test t : tests) {
            if (t.getId() == testId) {
                test = t;
                break;
            }
        }
        if (test == null) {
            // gérer le cas où le test n'existe pas pour cette candidature
            return;
        }

    }

    @Override
    public float calculateAverageTestScore() {
        List<AdmissionCandidacy> allCandidacies = admissionCandidacyRepository.findAll();
        float totalScore = 0.0f;
        for (AdmissionCandidacy candidacy : allCandidacies) {
            totalScore += candidacy.getTestScore();
        }
        return totalScore / allCandidacies.size();
    }
    @Override
    public Map<String, Double> getSpecialityTestAverages() {
        List<AdmissionCandidacy> allCandidacies = admissionCandidacyRepository.findAll();
        Map<String, List<Float>> specialityScoresMap = new HashMap<>();
        for (AdmissionCandidacy candidacy : allCandidacies) {
            String speciality = candidacy.getSpeciality();
            Float testScore = candidacy.getTestScore();
            if (speciality != null && testScore != null) {
                if (specialityScoresMap.containsKey(speciality)) {
                    specialityScoresMap.get(speciality).add(testScore);
                } else {
                    List<Float> scoresList = new ArrayList<>();
                    scoresList.add(testScore);
                    specialityScoresMap.put(speciality, scoresList);
                }
            }
        }
        Map<String, Double> specialityAveragesMap = new HashMap<>();
        for (Map.Entry<String, List<Float>> entry : specialityScoresMap.entrySet()) {
            String speciality = entry.getKey();
            List<Float> scoresList = entry.getValue();
            Double average = scoresList.stream().mapToDouble(Float::doubleValue).average().orElse(0.0);
            specialityAveragesMap.put(speciality, average);
        }
        return specialityAveragesMap;
    }

    @Override
    public AdmissionCandidacy getAdmissionCandidacyByIdCondidate(int id){
        User candidate=userRepository.findById(id).orElse(null);
        return (AdmissionCandidacy) admissionCandidacyRepository.findByUser(candidate);
    }


    @Override
    public AdmissionCandidacy updateAdmissionCandidacyStatus(int id, String status){
        AdmissionCandidacy admissionCandidacy =admissionCandidacyRepository.findById(id).get();
        admissionCandidacy.setStatus(status);
        return admissionCandidacyRepository.save(admissionCandidacy);
    }
    @Override
    public AdmissionCandidacy updateAdmissionCandidacyScore(int id, Float score){
        AdmissionCandidacy admissionCandidacy =admissionCandidacyRepository.findById(id).get();
        admissionCandidacy.setStatus("being processed");
        admissionCandidacy.setInterviewScore(score);
        return admissionCandidacyRepository.save(admissionCandidacy);
    }
    }



