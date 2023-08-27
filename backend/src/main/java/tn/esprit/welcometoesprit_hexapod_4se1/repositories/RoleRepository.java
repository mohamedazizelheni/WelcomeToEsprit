package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);
}
