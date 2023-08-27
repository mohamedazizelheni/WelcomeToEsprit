package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.Response.PostResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface IPostService {
    PostResponse getPostResponseById(int postId);

    Post getPostById(int postId);
    List<Post> getPost();
    List<Post> getPostBySentiment(String sentiment);
    Post createNewPost(String content, Principal principal);

    Map<String, Float> getPostCountByThemeCategory();

    Post updatePost(int postId, String content);
    void deletePost(int postId);
    List<Post> recherchePost(String content);
     List<Post> getTopRatedPosts();
     Map<String, Integer> getPostCountByPeriod();
    int predictNumPostsThisMonth();
}
