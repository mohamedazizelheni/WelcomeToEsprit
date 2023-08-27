package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Achievement;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Event;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Invitation;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.List;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation,Integer> {
    @Query("SELECT u FROM User u WHERE u.speaker =:true")
    List<User> findUserSpeakers(@Param("true") Boolean speaker);
    @Query("SELECT u FROM User u WHERE u.speaker =:false")
    List<User> findUsersStudents(@Param("false") Boolean speaker);
    @Query("SELECT u FROM User u WHERE u.classe LIKE CONCAT(:classe, '%')")
    List<User> findUserStudents(@Param("classe") String classe);

    @Query("SELECT i FROM Invitation i,Event e WHERE i.event.name=:v4 and i.event.id=e.id")
    List<Invitation> findUserStudentss(@Param("v4") String classe);
    @Query("SELECT i FROM Invitation i,User u,Event e WHERE i.user.classe=:classe and i.user.id=u.id and i.event.id=e.id")
    List<User> findUserStudentsInvited(@Param("classe") String classe);
    List<Invitation> findByEventId(int eventId);
    @Query("select a from Achievement  a , Event e where a.event.id=e.id")
    List<Invitation> findEventId(int eventId);
    @Query("select i from Invitation i ,User u , Event e where i.present=:true and i.user.id=u.id and i.event.id=e.id")
    List<User> findInvitedPresent(@Param("true") Boolean present);
}
