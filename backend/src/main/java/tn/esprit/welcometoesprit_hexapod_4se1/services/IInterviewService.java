package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IInterviewService {
    Interview createNewInterview(String day, String hour, int k) throws ParseException;
    void deleteInterviewById(int id);
    Interview getInterviewById(int id);

    List<String> getDays(int k);

    List<String> getHours(String date, int k) throws ParseException;

    List<Interview> getInterviewsByUserId(int id);


    Interview getInterviewByOfferCandidacyId(int id);

    Interview getInterviewByAdmissionCandidacyId(int id);

    Interview updateInterview(int interview_id,String day, String hour,int k) throws ParseException;

}
