package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.Response.CommentResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.List;

public interface ICommentService {
    Comment getCommentById(int commentId);
    Comment createNewComment(String content, int postId, Principal principal);
    Comment updateComment(int commentId, String content, int postId);

    void deleteComment(int commentId);
    List<Comment> getPostComments(int postId);
    Comment createNewRepComment(String content, int postId, int commentId);
    List<Comment> getMostRelevantComments(int postId);

}


