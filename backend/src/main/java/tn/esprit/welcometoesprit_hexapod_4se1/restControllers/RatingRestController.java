package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.EmptyCommentException;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.PostNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.Response.CommentResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.Response.RatingResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Rating;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IRatingService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/rating")
@SecurityRequirement(name = "bearerAuth")
public class RatingRestController {
    @Autowired
    IRatingService iRatingService;

    @PostMapping("/posts/{postId}/ratings/create")
    public ResponseEntity<?> createNewRating(@PathVariable("postId") int postId,
                                             @RequestParam(value = "value") Integer value, Principal principal) {

        Rating savedRating = iRatingService.createNewRating(value,postId,principal );
        RatingResponse ratingResponse = RatingResponse.builder()
                .rating(savedRating)
                .build();
        return new ResponseEntity<>(ratingResponse, HttpStatus.OK);
    }
    @PutMapping("/rating/{ratingId}/update")
    public ResponseEntity<?> updatePostRating(
                                              @PathVariable("ratingId") int ratingId,
                                              @RequestParam("stars") Integer value) {
        Rating updatedRating = iRatingService.updateRating(ratingId, value);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}/ratings")
    public ResponseEntity<List<Rating>> getPostRating(@PathVariable int postId) {
        try {
            List<Rating> ratings = iRatingService.getPostRating(postId);
            return ResponseEntity.ok(ratings);
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/ratings/{ratingId}/delete")
    public ResponseEntity<?> deleteRating(@PathVariable("ratingId") int ratingId)
    {
        iRatingService.deleteRating(ratingId);
        return ResponseEntity.ok("Rating has been deleted successfully");
    }





}
