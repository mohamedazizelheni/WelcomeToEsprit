package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.Response.RatingResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.List;

public interface IRatingService {
    Rating getRatingById(int ratingId);
    Rating createNewRating(Integer value, int postId, Principal principal);
    Rating updateRating(int ratingId, Integer value );
    List<Rating> getPostRating(int postId);
    void deleteRating(int ratingId);
}
