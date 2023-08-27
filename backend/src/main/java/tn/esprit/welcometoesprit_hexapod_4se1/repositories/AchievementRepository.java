package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Achievement;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Event;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement,Integer> {
    @Query("SELECT e FROM Event e WHERE e.name = :name")
    Event findByName(@Param("name") String name);
    @Query("SELECT a FROM Achievement a WHERE a.name = :achievementName")
    Optional<Achievement> findByAchievementName(@Param("achievementName") String achievementName);
   @Query("SELECT a FROM Achievement a WHERE a.archived=:false ")
   List<Achievement> findByArchivements(@Param("false") Boolean archived);
    @Query("SELECT a FROM Achievement a WHERE a.archived=:true ")
    List<Achievement> findByArchivementsArchived(@Param("true") Boolean archived);
    @Query("select a from Achievement  a , Event e where a.event.id=e.id")
    List<Achievement> findByEventId(int eventId);
    @Query("SELECT a FROM Achievement a")
    List<Achievement> findAll();
}
