package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer> {
    List<Comment> findByPost(Post postId);



}
