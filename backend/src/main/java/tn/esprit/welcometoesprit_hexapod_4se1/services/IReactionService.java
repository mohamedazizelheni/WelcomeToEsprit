package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.Exception.CommentNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.List;

public interface IReactionService {
    Reaction addReactionToComment(TypeReaction type, int commentId, Principal principal);
    Reaction updateReactionToComment(TypeReaction type, int reactionId);
    void   deleteReactionToComment(int reactionId);
    Reaction getReactionsById(int reactionId);

    List<Reaction> getCommentReactions(int commentId) throws CommentNotFoundException;
}
