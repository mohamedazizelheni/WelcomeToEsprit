package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IOfferCandidacyService {
    OfferCandidacy createNewOfferCandidacy(OfferCandidacy offerCandidacy, Principal principal);

    OfferCandidacy getOfferCandidacyById(int id);

    List<OfferCandidacy> getOfferCandidaciesByIdOffer(int id);

    List<OfferCandidacy> getOfferCandidaciesByIdCondidate(int id);

    OfferCandidacy updateOfferCandidacy(OfferCandidacy offerCandidacy);


    void deleteOfferCandidacyById(int id);

    int deleteOfferCandidaciesByOfferId(int offer_id);

    Map<Date, Integer> getOfferCandidacyStatisticsByDate(int id);

    OfferCandidacy updateOfferCandidacyStatus(int id, String status);

    OfferCandidacy updateOfferCandidacyScore(int id, Float score);

    OfferCandidacy getOfferCandidaciesByOfferIdAndUserId(int offerId, int userId);
}
