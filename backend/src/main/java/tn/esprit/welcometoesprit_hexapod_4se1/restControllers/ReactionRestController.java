package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Reaction;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.TypeReaction;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IReactionService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/reaction")
@SecurityRequirement(name = "bearerAuth")
public class ReactionRestController {
    @Autowired
    IReactionService iReactionService;


    @PostMapping("/comments/{commentId}/reactions/create")
    public ResponseEntity<Reaction> addReactionToComment(@PathVariable int commentId,
                                                         @RequestParam TypeReaction type, Principal principal)
                                                          {
        Reaction reaction = iReactionService.addReactionToComment(type, commentId,principal);
        return ResponseEntity.ok(reaction);
    }

    @PutMapping("/reactions/{reactionId}/update")
    public ResponseEntity<Reaction> updateReactionToComment(@PathVariable int reactionId,
                                                         @RequestParam TypeReaction type)
    {
        Reaction reaction = iReactionService.updateReactionToComment(type, reactionId);
        return ResponseEntity.ok(reaction);

    }
    @DeleteMapping("/reactions/{reactionId}/delete")
    public ResponseEntity<String> deleteReactionToComment(@PathVariable int reactionId)
    {
        iReactionService.deleteReactionToComment( reactionId);
        return ResponseEntity.ok("Reaction has been deleted successfully");

    }

    @GetMapping("/coments/{commentId}/reactions")
    public ResponseEntity<List<Reaction>> getCommentReactions(@PathVariable("commentId") int commentId) {
        List<Reaction> reactions = iReactionService.getCommentReactions(commentId);
        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }











}
