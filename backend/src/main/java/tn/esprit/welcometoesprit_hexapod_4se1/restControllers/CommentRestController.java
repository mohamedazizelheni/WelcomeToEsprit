package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.EmptyCommentException;

import tn.esprit.welcometoesprit_hexapod_4se1.Exception.PostNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.Response.CommentResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.services.ICommentService;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentRestController {
    @Autowired
    ICommentService iCommentService;



    @PostMapping("/posts/{postId}/comments/create")
    public ResponseEntity<?> createNewComment(@PathVariable("postId") int postId,
                                              @RequestParam(value = "content") String content, Principal principal) {
        if (content == null || content.trim().isEmpty()) {
            throw new EmptyCommentException("Comment content cannot be empty");
        }
        Comment savedComment = iCommentService.createNewComment(content,postId ,principal);
        CommentResponse commentResponse = CommentResponse.builder()
                .comment(savedComment)
                //.likedByAuthUser(false)
                .build();
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/create")
    public ResponseEntity<?> createNewRepComment(@PathVariable("postId") int postId,
                                              @RequestParam(value = "content") String content,
                                                 @PathVariable("commentId") int commentId) {
        if (content == null || content.trim().isEmpty()) {
            throw new EmptyCommentException("Comment content cannot be empty");
        }
        Comment savedComment = iCommentService.createNewRepComment(content,postId,commentId );
        CommentResponse commentResponse = CommentResponse.builder()
                .comment(savedComment)
                //.likedByAuthUser(false)
                .build();
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}/update")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") int commentId,
                                               @PathVariable("postId") int postId,
                                               @RequestParam(value = "content") String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new EmptyCommentException("Comment content cannot be empty");
        }
        Comment savedComment = iCommentService.updateComment(commentId, content, postId);

        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId)
   {
        iCommentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment has been deleted successfully");
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getPostComments(@PathVariable("postId") int postId) {
        List<Comment> comments = iCommentService.getPostComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments/relevant")
    public ResponseEntity<List<Comment>> getMostRelevantComments(@PathVariable int postId) {
        try {
            List<Comment> relevantComments = iCommentService.getMostRelevantComments(postId);
            return ResponseEntity.ok(relevantComments);
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

















}
