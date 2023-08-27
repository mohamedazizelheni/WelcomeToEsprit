package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

@Repository
public interface AdmissionCandidacyRepository extends CrudRepository<AdmissionCandidacy,Integer> {
    // List<AdmissionCandidacy>findById(int id);
    AdmissionCandidacy findAdmissionCandidaciesByTestsId(int id);

    AdmissionCandidacy findByTestsId(int id);

    List<AdmissionCandidacy> findAll();
    AdmissionCandidacy findByUser(User user);

}
