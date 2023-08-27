package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Ads;

@Repository
public interface AdsRepository extends CrudRepository<Ads,Integer> {
    @Query("select count(a.categorieads) from Ads a   where a.socity = :socity")
    public long countBySocity(@Param("socity") String socity);

}
