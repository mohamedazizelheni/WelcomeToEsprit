package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Question;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Test;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Integer> {
    List<Question> findAllByType(String type);
    List<Question> findAll();
    Question findQuestionByFalse1(String reponse);
    Question findQuestionByFalse2(String reponse);
    Question findQuestionByFalse3(String reponse);
    Question findQuestionByTrue1(String reponse);
     List<Question> findQuestionByTestsId(int id);

}
