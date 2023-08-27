package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.UpDown;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.UpDownId;

public interface UpDownRepository extends JpaRepository<UpDown, UpDownId> {
}