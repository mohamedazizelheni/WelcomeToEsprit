package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.InterviewRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.OfferCandidacyRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.OfferRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.security.Principal;
import java.util.*;

@Service
public class OfferCandidacyService implements IOfferCandidacyService{
    @Autowired
    OfferCandidacyRepository offerCandidacyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    InterviewRepository interviewRepository;
    @Override
    public OfferCandidacy createNewOfferCandidacy(OfferCandidacy offerCandidacy,Principal principal){

        User user = userRepository.findUserByMail(principal.getName()).orElse(null);
        offerCandidacy.setUser(user);
        offerCandidacy.setCreationDate(new Date());
        offerCandidacy.setStatus("waiting for interview");
        return offerCandidacyRepository.save(offerCandidacy);
    }
    @Override
    public OfferCandidacy getOfferCandidacyById(int id){
        return offerCandidacyRepository.findById(id).orElse(null);
    }
    @Override
    public List<OfferCandidacy> getOfferCandidaciesByIdOffer(int id){
        Offer offer=offerRepository.findById(id).orElse(null);
        return (List<OfferCandidacy>) offerCandidacyRepository.findAllByOffer(offer);
    }
    @Override
    public List<OfferCandidacy> getOfferCandidaciesByIdCondidate(int id){
        User candidate=userRepository.findById(id).orElse(null);
        return (List<OfferCandidacy>) offerCandidacyRepository.findAllByUser(candidate);
    }
    @Override
    public OfferCandidacy updateOfferCandidacy(OfferCandidacy offerCandidacy){
        return offerCandidacyRepository.save(offerCandidacy);
    }
    @Override
    public void deleteOfferCandidacyById(int id){
        offerCandidacyRepository.deleteById(id);
    }
    @Override
    public int deleteOfferCandidaciesByOfferId(int offer_id){
        Offer offer = offerRepository.findById(offer_id).orElse(null);
        List<OfferCandidacy> candidacies = (List<OfferCandidacy>) offerCandidacyRepository.findAllByOffer(offer);
        List<OfferCandidacy> candidacies1 = new ArrayList<OfferCandidacy>();
        List<Interview> interviews = new ArrayList<Interview>();
        int i=0;
        if(candidacies.size()==0)
            i=-1;
        else{
            for (OfferCandidacy offerCandidacy: candidacies) {
                if (offerCandidacy.getInterview().getComplain()==null){
                    candidacies1.add(offerCandidacy);
                    interviews.add(offerCandidacy.getInterview());
                }
                else
                    i++;
            }
            offerCandidacyRepository.deleteAll(candidacies1);
            interviewRepository.deleteAll(interviews);
        }

        return i;
    }
    @Override
    public Map<Date, Integer> getOfferCandidacyStatisticsByDate(int id) {
        Offer offer = offerRepository.findById(id).get();
        List<Object[]> result = offerCandidacyRepository.getCandidacyStatisticsByDate(offer);
        Map<Date, Integer> resultMap = new HashMap<>();
        for (Object[] obj : result) {
            Date date = (Date) obj[0];
            Integer count = ((Number) obj[1]).intValue();
            resultMap.put(date, count);
        }
        return resultMap;
    }
    @Override
    public OfferCandidacy updateOfferCandidacyStatus(int id, String status){
        OfferCandidacy offerCandidacy =offerCandidacyRepository.findById(id).get();
        offerCandidacy.setStatus(status);
        return offerCandidacyRepository.save(offerCandidacy);
    }
    @Override
    public OfferCandidacy updateOfferCandidacyScore(int id, Float score){
        OfferCandidacy offerCandidacy =offerCandidacyRepository.findById(id).get();
        offerCandidacy.setStatus("being processed");
        offerCandidacy.setScore(score);
        return offerCandidacyRepository.save(offerCandidacy);
    }
    @Override
    public  OfferCandidacy getOfferCandidaciesByOfferIdAndUserId(int offerId, int userId){
        return offerCandidacyRepository.getOfferCandidaciesByOfferIdAndUserId(offerId,userId);
    }
}
