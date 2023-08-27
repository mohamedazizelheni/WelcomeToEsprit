package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLastName(String lastName);
    Optional<User> findUserByMail(String mail);
    User findUserByCin(String cin);
    List<User> findUserByRoleRole(String role);
    User findByFirstName(String name);
    List<User> findByLastNameContaining(String lastName);

}
