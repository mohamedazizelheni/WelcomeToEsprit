package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.FAQ;

@Repository
public interface FAQRepository extends CrudRepository<FAQ,Integer> {
    @Query("select count(R) from UpDown R  where R.value=1 and R.upDownId.faq= :faq")
    public Integer countnbUpbypost(@Param("faq") FAQ faq);

    @Query("select count(R) from UpDown R  where R.value=2 and R.upDownId.faq= :faq")
    public Integer countnbdesDownbypost(@Param("faq") FAQ faq);
}
