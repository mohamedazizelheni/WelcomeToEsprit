package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Achievement;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Event;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
    @Query("SELECT e FROM Event e")
    List<Event> findAll();
    List<Event> findByDate(Date date);

    @Query("SELECT i FROM Invitation i,Event e WHERE i.event.name=:nameEvent and i.event.id=e.id")
    Event findEventByName(@Param("nameEvent") String nameEvent);

}
