package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.ResourceNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AchievementRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.EventRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementService implements IAchievementService {
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public Achievement addAchievementandAffecteEvent(Achievement achievement){

//        Event event = achievementRepository.findByName(name);
//        if (event == null ){
//            throw new EntityNotFoundException("Event not Found");
//        }
//        // create a new achievement and assign it to the event
//        Achievement achievement = new Achievement();
//        achievement.setName(name);
//        achievement.setEvent(event);
//        achievement.setVideo(video.getBytes());

        return achievementRepository.save(achievement);
    }
    @Override
    public void archiveAchievement(String name) {
        Optional<Achievement> achievements = achievementRepository.findByAchievementName(name);

        if (achievements.isPresent()) {
            Achievement achievement = achievements.get();
            achievement.setArchived(true);
            achievementRepository.save(achievement);
        } else {
            throw new EntityNotFoundException("Achievement not found");
        }
    }
    @Override
    public List<Achievement> getAchievement() {
        List<Achievement> achievements= achievementRepository.findByArchivements(Boolean.FALSE);
        return achievements;

    }
    @Override
    public List<Achievement> getAllAchievement() {
        List<Achievement> achievements= achievementRepository.findAll();
        return achievements;

    }
    @Override
    public List<Achievement> getAchievementArchived() {
        List<Achievement> achievements= achievementRepository.findByArchivementsArchived(Boolean.TRUE);
        return achievements;

    }
    @Override
    public Achievement updateAchievement(int achievementId,String nameUpdate, MultipartFile video) throws IOException {
        Achievement achievement = achievementRepository.findById(achievementId).orElseThrow(() -> new ResourceNotFoundException("Achievement", "id", achievementId));
        achievement.setName(nameUpdate);
        // Convert the MultipartFile to a byte array
        byte[] imageData = video.getBytes();

        // Set the new image data on the achievement entity
        achievement.setVideo(video.getBytes());

        return achievementRepository.save(achievement);
    }
    @Override
    public Achievement getAchievementById(int id){
        return achievementRepository.findById(id).orElse(null);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAchievements() {
        Date sevenDaysLater = Date.from(LocalDateTime.now().minusDays(7).toInstant(ZoneOffset.UTC));

        System.out.println("Updating achievements...");
        List<Event> events = eventRepository.findAll();
        Date currentDate = new Date();
        for (Event event : events) {
            if (currentDate.before(DateUtils.addDays(sevenDaysLater,7))) {
                List<Achievement> achievements = achievementRepository.findByEventId(event.getId());
                for (Achievement achievement : achievements) {
                    achievement.setArchived(Boolean.TRUE);
                    achievementRepository.save(achievement);
                }
            }
        }
    }
    @Override
    public List<Achievement> findAchievemntsByEventId(int id) {
        Event event = eventRepository.findById(id).get();
        List<Achievement> achievements = achievementRepository.findByEventId(event.getId());
        return achievements;
    }
}
