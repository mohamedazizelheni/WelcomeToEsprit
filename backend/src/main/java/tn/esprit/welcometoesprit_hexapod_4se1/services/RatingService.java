package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.PostNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.RatingNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.Response.RatingResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.PostRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.RatingRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class RatingService implements IRatingService{
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Rating getRatingById(int ratingId) {
        return  ratingRepository.findById(ratingId).orElse(null);
    }

    @Override
    public Rating createNewRating(Integer value,  int postId, Principal principal) {
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);

        Rating newRating = new Rating();
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post id"));
        if (value < 0 || value > 5) {
            throw new IllegalArgumentException("Invalid rating value");
        }
        newRating.setValue(value);
        newRating.setDate(new Date());
        newRating.setPost(post);
        newRating.setUser(user);
        newRating= ratingRepository.save(newRating);

        List<Rating> ratings= post.getRatings();
        float avgRating = (float) ratings.stream().mapToDouble(Rating::getValue).average().orElse(0);
        float roundedAvgRating = Math.round(avgRating * 10) / 10f;
        post.setRating(roundedAvgRating);
        postRepository.save(post);
        return newRating;
    }

    @Override
    public Rating updateRating(int ratingId, Integer value) {
        Rating ratingToUpdate = ratingRepository.findById(ratingId).orElse(null);
        if (ratingToUpdate == null) {
            throw new RatingNotFoundException("Rating not found with id: " + ratingId);
        }
        if (value < 0 || value > 5) {
            throw new IllegalArgumentException("Invalid rating value");
        }
        ratingToUpdate.setValue(value);
        ratingRepository.save(ratingToUpdate);

        Post post = ratingToUpdate.getPost();
        List<Rating> ratings = post.getRatings();
        float avgRating = (float) ratings.stream().mapToInt(Rating::getValue).average().orElse(0);
        float roundedAvgRating = Math.round(avgRating * 10) / 10f;
        post.setRating(roundedAvgRating);
        postRepository.save(post);

        return ratingToUpdate;
    }

    @Override
    public List<Rating> getPostRating(int postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        return post.getRatings();
    }

    @Override
    public void deleteRating(int ratingId) {
        Rating ratingToDelete = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found with id " + ratingId));
        Post post = ratingToDelete.getPost();
        List<Rating> ratings =post.getRatings();
        ratingRepository.delete(ratingToDelete);

        float avgRating = (float) ratings.stream().mapToInt(Rating::getValue).average().orElse(0);
        float roundedAvgRating = Math.round(avgRating * 10) / 10f;
        post.setRating(roundedAvgRating);
        postRepository.save(post);
    }
}
