package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

public interface IInvitationService {

    List<User> getUserSpeakers();

    List<User> getUsersStudents();

    Invitation inviterSpeaker(int event_id, int user_id);

    void inviterStudents(int event_id, String niveau);

    List<Invitation> getInvitations(String name);



    void updatePresence(int id, boolean present);
}
