package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.ComplainRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.InterviewRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class ComplainService implements IComplainService{
    @Autowired
    ComplainRepository complainRepository;
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Complain createNewComplain(Complain complain, int interview_id, Principal principal){
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);
        complain.setDate(new Date());
        complain.setUser(user);
        Interview interview=interviewRepository.findById(interview_id).get();
        complain.setInterview(interview);
        return complainRepository.save(complain);
    }
    @Override
    public Complain getComplainById(int id){
        return complainRepository.findById(id).orElse(null);
    }
    @Override
    public List<Complain> getComplainsByIdComplainer(int id){
        return complainRepository.getComplainsByUserId(id);
    }
    @Override
    public List<Complain> getAllComplains(){
        return (List<Complain>) complainRepository.findAll();
    }
    @Override
    public Complain updateComplain(Complain complain){
        Complain complain1 = complainRepository.findById(complain.getId()).get();
        complain1.setTitle(complain.getTitle());
        complain1.setDescription(complain.getDescription());
        complainRepository.save(complain1);
        return complain1;
    }
    @Override
    public void deleteComplain(int id){
        complainRepository.deleteById(id);
    }
}
