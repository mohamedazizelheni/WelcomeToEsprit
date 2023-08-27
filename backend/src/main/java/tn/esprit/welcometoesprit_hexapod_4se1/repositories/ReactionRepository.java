package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Reaction;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction,Integer> {
}
