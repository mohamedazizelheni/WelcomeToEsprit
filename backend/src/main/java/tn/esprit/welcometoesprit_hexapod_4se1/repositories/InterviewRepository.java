package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Interview;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Room;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface InterviewRepository extends CrudRepository<Interview,Integer> {

    @Query("SELECT u FROM User u WHERE u.jury=true and NOT EXISTS (SELECT i FROM Interview i WHERE i.user = u AND i.date = :date)")
    List<User> findAvailableJuries(@Param("date") Date date);
    @Query("SELECT u FROM User u WHERE u.evaluator=true and NOT EXISTS (SELECT i FROM Interview i WHERE i.user = u AND i.date = :date)")
    List<User> findAvailableEvaluators(@Param("date") Date date);
    @Query("SELECT room from Room room WHERE  NOT EXISTS (SELECT i FROM Interview i WHERE i.room = room AND i.date = :date)")
    List<Room> findAvailableRooms(@Param("date") Date date);
    Interview getInterviewByOfferCandidacyId(int id);
    Interview getInterviewByAdmissionCandidacyId(int id);
    List<Interview> findInterviewByUserId(int id);

}
