package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Offer;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.OfferCandidacy;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.List;

@Repository
public interface OfferCandidacyRepository extends CrudRepository<OfferCandidacy,Integer> {
    List<OfferCandidacy> findAllByOffer(Offer offer);
    List<OfferCandidacy> findAllByUser(User user);
    OfferCandidacy getOfferCandidaciesByOfferIdAndUserId(int offerId, int userId);
    @Query("SELECT date(o.creationDate), COUNT(o) FROM OfferCandidacy o WHERE o.offer = :offer GROUP BY date(o.creationDate)")
    List<Object[]>  getCandidacyStatisticsByDate(@Param("offer") Offer offer);
}
