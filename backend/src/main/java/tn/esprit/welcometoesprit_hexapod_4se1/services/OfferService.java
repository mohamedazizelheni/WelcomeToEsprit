package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.OfferRepository;

import java.util.Date;
import java.util.List;

@Service
public class OfferService implements IOfferService{
    @Autowired
    OfferRepository offerRepository;
    @Override
    public Offer createNewOffer(Offer offer){
        offer.setCreationDate(new Date());
        return offerRepository.save(offer);}
    @Override
    public Offer updateOffer(Offer offer){
        return offerRepository.save(offer);
    }
    @Override
    public Offer getOfferById(int id){
        return offerRepository.findById(id).orElse(null);
    }
    @Override
    public List<Offer> getOffers(){
        return (List<Offer>) offerRepository.findAll();
    }
    @Override
    public void deleteOffer(int id){
        offerRepository.deleteById(id);
    }


}
