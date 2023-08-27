package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Postetheme;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
List<Post> findByTextContaining(String content);
    long countAllByDateBetween(Date startDate, Date endDate);
    List<Post> findBySentiment(String sentiment);
    long  countPostByThéme( Postetheme postetheme);

}
