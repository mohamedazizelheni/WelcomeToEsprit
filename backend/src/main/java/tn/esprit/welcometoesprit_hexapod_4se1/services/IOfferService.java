package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

public interface IOfferService {
    Offer createNewOffer(Offer offer);

    Offer updateOffer(Offer offer);

    Offer getOfferById(int id);

    List<Offer> getOffers();

    void deleteOffer(int id);

}
