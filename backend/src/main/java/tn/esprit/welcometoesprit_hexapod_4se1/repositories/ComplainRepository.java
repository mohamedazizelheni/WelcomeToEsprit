package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Complain;

import java.util.List;

@Repository
public interface ComplainRepository extends CrudRepository<Complain,Integer> {
    List<Complain> getComplainsByUserId(int id);

}
