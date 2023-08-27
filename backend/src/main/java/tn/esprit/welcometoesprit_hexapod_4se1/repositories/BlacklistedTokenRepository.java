package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.BlacklistedToken;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Integer> {
    Optional<BlacklistedToken> findByToken(String token);
    void deleteAllByRemovingTimeBefore(Date removingTime);
}

