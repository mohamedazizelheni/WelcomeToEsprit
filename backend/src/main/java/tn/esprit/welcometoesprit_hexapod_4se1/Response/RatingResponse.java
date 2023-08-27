package tn.esprit.welcometoesprit_hexapod_4se1.Response;


import lombok.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Rating;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private Rating rating;
}
