package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface IAdmissionCandidacyService {

    List<AdmissionCandidacy> getAdmissionCandidacy();

    AdmissionCandidacy createNewAdmissionCandidacy(AdmissionCandidacy admissionCandidacy, Principal principal);

    AdmissionCandidacy getAdmissionCandidacyById(int id);

    AdmissionCandidacy updateAdmissionCandidacy(AdmissionCandidacy admissionCandidacy);

    void  removeAdmissionCandidacy(int id);

    void updateTestScore(int candidacyId, int testId, float testScore);

    float calculateAverageTestScore();

    Map<String, Double> getSpecialityTestAverages();

    AdmissionCandidacy getAdmissionCandidacyByIdCondidate(int id);

    AdmissionCandidacy updateAdmissionCandidacyStatus(int id, String status);

    AdmissionCandidacy updateAdmissionCandidacyScore(int id, Float score);
}
