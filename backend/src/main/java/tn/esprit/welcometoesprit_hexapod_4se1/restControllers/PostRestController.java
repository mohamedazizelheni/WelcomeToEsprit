package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.EmptyPostException;
import tn.esprit.welcometoesprit_hexapod_4se1.Response.PostResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IPostService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.SentimentAnalyzer;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/post")
@SecurityRequirement(name = "bearerAuth")
public class PostRestController {
     @Autowired
    IPostService iPostService;
    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;



    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable("postId") int postId) {
        PostResponse foundPostResponse = iPostService.getPostResponseById(postId);
        return new ResponseEntity<>(foundPostResponse, HttpStatus.OK);
    }
    @GetMapping("/posts/AllPost")
    public List<Post>  getAllPosts() {
        List<Post> publications = iPostService.getPost();

        publications.sort(Comparator.comparing(Post::getDate).reversed());

        return publications;
    }
    @GetMapping("/posts/PostSentiment")
    public List<Post>  getPostBySentiment(@RequestParam String sentiment) {
        return iPostService.getPostBySentiment(sentiment);
    }

    @PostMapping("/posts/create")
    public ResponseEntity<Post> createNewPost(@RequestParam(required = false) String content, Principal principal) {
        if (content == null || content.trim().isEmpty()) {
            throw new EmptyPostException("Post content cannot be empty");
        }
        Post createdPost = iPostService.createNewPost(content,principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
    @PutMapping("/posts/{postId}/update")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") int postId, @RequestParam("content") String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new EmptyPostException("Post content cannot be empty");
        }
        Post updatedPost = iPostService.updatePost(postId, content);
        return ResponseEntity.ok(updatedPost);
    }
    @DeleteMapping("/posts/{postId}/delete")
    public ResponseEntity<String> deletePost(@PathVariable("postId") int postId) {
        iPostService.deletePost(postId);
        return ResponseEntity.ok("Post has been deleted successfully");
    }

    @GetMapping("/recherchePost")
    public List<Post> recherchePost(@RequestParam("Text") String content, @RequestParam(value="Filter", required = false, defaultValue = "date") String filter
                                    ){

    List<Post> publications = iPostService.recherchePost(content);

        if (filter.equals("rating")) {
            publications.sort(Comparator.comparing(Post::getRating).reversed());
        } else  {
            publications.sort(Comparator.comparing(Post::getDate).reversed());
        }
        return publications;
}
    @GetMapping("/posts/top-rated")
    public ResponseEntity<List<Post>> getTopRatedPosts() {
        List<Post> topRatedPosts = iPostService.getTopRatedPosts();
        return new ResponseEntity<>(topRatedPosts, HttpStatus.OK);
    }

    @GetMapping("/post-count-by-period")
    public ResponseEntity<Map<String, Integer>> getPostCountByPeriod() {
        Map<String, Integer> postCountByPeriod = iPostService.getPostCountByPeriod();
        return ResponseEntity.ok(postCountByPeriod);
    }
    @GetMapping("/predict")
    public ResponseEntity<Map<String, Integer>> predictNumPostsTomorrow() {
        int numPosts = iPostService.predictNumPostsThisMonth();
        Map<String, Integer> response = new HashMap<>();
        response.put("number of post", numPosts);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/analyze")
    public ResponseEntity<SentimentAnalyzer.SentimentResult> analyzeSentiment(@RequestBody String text) {
        SentimentAnalyzer.SentimentResult result = sentimentAnalyzer.analyzeSentiment(text);
        return ResponseEntity.ok(result);
    }
    @GetMapping("stat")
    public Map<String, Float> statofpostseloncategorie(){
        return iPostService.getPostCountByThemeCategory();
    }


/*
    @PostMapping("/speech-to-text")
    public String speechToText(@RequestParam("audioFile") MultipartFile audioFile) {
        try {
            InputStream inputStream = new ByteArrayInputStream(audioFile.getBytes());
            SpeechConfig speechConfig = SpeechConfig.fromSubscription("<votre-clé-d'abonnement>", "<votre-région>");
            String recognizedText = SpeechRecognition.recognizeFromMicrophone(speechConfig, inputStream);
            return recognizedText;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Une erreur s'est produite lors de la conversion de la parole en texte : " + ex.getMessage();
        }
    }

 */

}
