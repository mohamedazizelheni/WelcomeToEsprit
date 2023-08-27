package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.CommentNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.CommentRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.ReactionRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class ReactionService implements IReactionService{
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    public Reaction addReactionToComment(TypeReaction type, int commentId, Principal principal) {
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);

        Reaction reaction = new Reaction();
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id " + commentId));

        reaction.setType(type);
        reaction.setComment(comment);
        reaction.setDate(new Date());
        reaction.setUser(user);
        reactionRepository.save(reaction);

        int totReaction= comment.getReactions().size();
        comment.setTotReaction(totReaction);
        commentRepository.save(comment);
        return reaction;

    }

    public Reaction updateReactionToComment(TypeReaction type, int reactionId){

        Reaction reactionToUpdate = reactionRepository.findById(reactionId).orElse(null);
        reactionToUpdate.setType(type);
        reactionRepository.save(reactionToUpdate);

        return null;
    }

    public void   deleteReactionToComment(int reactionId){
        Reaction reactionToDelete= reactionRepository.findById(reactionId).orElse(null);
        Comment comment = reactionToDelete.getComment();
        reactionRepository.delete(reactionToDelete);
        int totalReactions = comment.getReactions().size();
        comment.setTotReaction(totalReactions);
        commentRepository.save(comment);

    }
    public Reaction getReactionsById(int reactionId){
        return reactionRepository.findById(reactionId).orElse(null) ;
    }
    @Override
    public List<Reaction> getCommentReactions(int commentId) throws CommentNotFoundException {
        Comment com = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException());

        return com.getReactions();
    }







}
