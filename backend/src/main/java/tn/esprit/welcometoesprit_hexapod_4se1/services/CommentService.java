package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.CommentNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.PostNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.appConfig.ForbiddenWords;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.CommentRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.PostRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class    CommentService implements ICommentService{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ForbiddenWords forbiddenWords;
@Autowired
    UserRepository userRepository;


    @Override
    public Comment getCommentById(int commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public Comment createNewComment(String content, int postId, Principal principal) {
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);

        if (forbiddenWords.checkForForbiddenWords(content)) {
            content = forbiddenWords.filter(content);
        }
        Comment newComment = new Comment();
        Post post = postRepository.findById(postId).orElse(null);

        newComment.setText(content);
        newComment.setPost(post);
        newComment.setDate(new Date());
        newComment.setUser(user);
        post.setCommentCount(post.getCommentCount()+1);
        return commentRepository.save(newComment);
    }
    @Override
    public Comment createNewRepComment(String content, int postId, int commentId) {
        if (forbiddenWords.checkForForbiddenWords(content)) {
            content = forbiddenWords.filter(content);
        }
        Comment newRepComment = new Comment();
        Post post = postRepository.findById(postId).orElse(null);
        Comment rep= commentRepository.findById(commentId).orElse(null);

        newRepComment.setText(content);
        newRepComment.setPost(post);
        newRepComment.setComment(rep);
        newRepComment.setDate(new Date());
        return commentRepository.save(newRepComment);
    }
    @Override
    public Comment updateComment(int commentId, String content, int postId) {
        if (forbiddenWords.checkForForbiddenWords(content)) {
            content = forbiddenWords.filter(content);
        }
        Comment targetComment = getCommentById(commentId);
        Post post = postRepository.findById(postId).orElse(null);

        targetComment.setText(content);
        targetComment.setPost(post);

        return commentRepository.save(targetComment);


    }

    @Override
    public void deleteComment(int commentId) {

        commentRepository.deleteById(commentId);
    }



    @Override
    public List<Comment> getPostComments(int postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());

        return post.getComments();
    }



        public List<Comment> getMostRelevantComments(int postId) throws PostNotFoundException {
            Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
            List<Comment> comments = post.getComments();
            comments.sort(Comparator.comparingInt(Comment::getTotReaction).reversed());
            return comments;
        }









}
