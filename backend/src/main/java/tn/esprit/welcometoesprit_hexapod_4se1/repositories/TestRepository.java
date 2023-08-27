package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Test;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test,Integer> {
    List<Test> findAllByType(String type);
    List<Test> findAll();
}
