package tn.esprit.welcometoesprit_hexapod_4se1.Response;


import lombok.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Comment comment;
}
