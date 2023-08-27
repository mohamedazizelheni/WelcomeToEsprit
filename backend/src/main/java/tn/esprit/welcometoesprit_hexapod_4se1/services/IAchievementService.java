package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.io.IOException;
import java.util.List;

public interface IAchievementService {


    Achievement addAchievementandAffecteEvent(Achievement achievement);

    void archiveAchievement(String name);

    List<Achievement> getAchievement();

    List<Achievement> getAllAchievement();

    List<Achievement> getAchievementArchived();

    Achievement updateAchievement(int achievementId, String nameUpdate, MultipartFile video) throws IOException;

    Achievement getAchievementById(int id);

    List<Achievement> findAchievemntsByEventId(int id);
}
