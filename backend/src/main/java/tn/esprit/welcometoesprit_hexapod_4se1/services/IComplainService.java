package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.List;

public interface IComplainService {

    Complain createNewComplain(Complain complain, int interview_id, Principal principal);

    Complain getComplainById(int id);
    List<Complain> getComplainsByIdComplainer(int id);


    List<Complain> getAllComplains();

    Complain updateComplain(Complain complain);

    void deleteComplain(int id);
}
