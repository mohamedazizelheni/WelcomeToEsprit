package tn.esprit.welcometoesprit_hexapod_4se1.services;


import org.apache.commons.lang3.tuple.Pair;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.PostNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.appConfig.ForbiddenWords;
import tn.esprit.welcometoesprit_hexapod_4se1.Response.PostResponse;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.PostRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.*;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService implements IPostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    private NLPService nlpService;
    private final ForbiddenWords forbiddenWords;

    @Autowired
    private UserRepository userRepository;


    @Override
    public PostResponse getPostResponseById(int postId) {
        Post foundPost = getPostById(postId);
        return PostResponse.builder()
                .post(foundPost)
                .build();
    }

    @Override
    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }
    @Override
    public List<Post> getPostBySentiment(String sentiment) {
        return  postRepository.findBySentiment(sentiment);
    }

    public List<Post> recherchePost(String content) {
        return postRepository.findByTextContaining(content);
    }

    public List<Post> getPost() {
        return postRepository.findAll();
    }

    @Override
    public Post createNewPost(String content, Principal principal) {
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);

        if (forbiddenWords.checkForForbiddenWords(content)) {
            content = forbiddenWords.filter(content);
        }

        Post newPost = new Post();
        newPost.setText(content);
        newPost.setDate(new Date());
        newPost.setUser(user);
        SentimentAnalyzer.SentimentResult sentimentResult = SentimentAnalyzer.analyzeSentiment(content);
        if (sentimentResult.getSentiment() > 0) {
            newPost.setSentiment("positive");
        } else if (sentimentResult.getSentiment() < 0) {
            newPost.setSentiment("negative");
        } else {
            newPost.setSentiment("neutre");
        }

        String theme = nlpService.answerQuestion(content);
        Postetheme theme1;
        theme1 = Postetheme.valueOf(theme);

        newPost.setThéme(theme1);
        Post savedPost = postRepository.save(newPost);
//        String message = "Votre publication a été créé avec succes ! " ;
//
//        twilioSmsService.sendSms("+21628528913", message);
        return savedPost;
    }
    @Override
    public Map<String, Float> getPostCountByThemeCategory() {
        Postetheme[] values = Postetheme.values();
        int x = values.length;
        Map<String, Float> postCounts = new HashMap<>();

        for (Postetheme theme : Postetheme.values()) {
            String category = theme.name();
            long count = postRepository.countPostByThéme(theme);
            postCounts.put(category, (float) (count*100)/x);
        }

        return postCounts;
    }


    @Override
    public Post updatePost(int postId, String content) {
        if (forbiddenWords.checkForForbiddenWords(content)) {
            content = forbiddenWords.filter(content);
        }
        Post targetPost = getPostById(postId);
        if (StringUtils.isNotEmpty(content)) {
            targetPost.setText(content);
        }
        SentimentAnalyzer.SentimentResult sentimentResult = SentimentAnalyzer.analyzeSentiment(content);
        if (sentimentResult.getSentiment() > 0) {
            targetPost.setSentiment("positive");
        } else if (sentimentResult.getSentiment() < 0) {
            targetPost.setSentiment("negative");
        } else {
            targetPost.setSentiment("neutre");
        }
        return postRepository.save(targetPost);
    }

    @Override
    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }

    @Scheduled(cron = "0 0 0 * * ?") // cette tâche s'exécute tous les jours à minuit
    public void cleanupPosts() {
        // calculer la date d'il y a un jour par rapport à la date et heure actuelles
        Date oneDayAgo = Date.from(LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC));

        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            if (post.getDate().before(oneDayAgo)) {
                List<Rating> ratings = post.getRatings();
                List<Comment> comments = post.getComments();
                if (ratings.isEmpty() && comments.isEmpty()) {
                    postRepository.delete(post);
                }
            }
        }
    }

    public List<Post> getTopRatedPosts() {
        List<Post> topRatedPosts = new ArrayList<>();
        List<Post> allPosts = postRepository.findAll();

        // Triez les publications par note décroissante
        allPosts.sort((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));

        // Prenez les 10 premières publications les mieux notées (vous pouvez changer ce nombre selon vos besoins)
        int numPosts = Math.min(10, allPosts.size());
        for (int i = 0; i < numPosts; i++) {
            topRatedPosts.add(allPosts.get(i));
        }

        return topRatedPosts;
    }

    public Map<String, Integer> getPostCountByPeriod() {
        Map<String, Integer> postCountByPeriod = new HashMap<>();
        // Calcul du nombre de publications d'aujourd'hui
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = LocalDateTime.of(today, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(today, LocalTime.MAX);
        long numPostsToday = postRepository.countAllByDateBetween(Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()), Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()));
        postCountByPeriod.put("today", (int) numPostsToday);


        // Calcul du nombre de publications par jour
        LocalDate ntoday = LocalDate.now();
        LocalDate oneDayAgo = ntoday.minusDays(1);
        long numPostsLastDay = postRepository.countAllByDateBetween(Date.from(oneDayAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(ntoday.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        postCountByPeriod.put("last_day", (int) numPostsLastDay);

        // Calcul du nombre de publications par semaine
        LocalDate oneWeekAgo = today.minusWeeks(1);
        long numPostsLastWeek = postRepository.countAllByDateBetween(Date.from(oneWeekAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        postCountByPeriod.put("last_week", (int) numPostsLastWeek);

        // Calcul du nombre de publications par mois
        LocalDate oneMonthAgo = today.minusMonths(1);
        long numPostsLastMonth = postRepository.countAllByDateBetween(Date.from(oneMonthAgo.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        postCountByPeriod.put("last_month", (int) numPostsLastMonth);

        return postCountByPeriod;
    }

    public int predictNumPostsThisMonth() {
        // Collect data on number of posts created each day
        List<Post> posts = postRepository.findAll();
        Map<LocalDate, Integer> postCountsByDate = new HashMap<>();
        for (Post post : posts) {
            LocalDate postDate = post.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int count = postCountsByDate.getOrDefault(postDate, 0);
            postCountsByDate.put(postDate, count + 1);
        }

        // Convert postCountsByDate to a list of (date, count) pairs
        List<Pair<Long, Integer>> data = new ArrayList<>();
        for (Map.Entry<LocalDate, Integer> entry : postCountsByDate.entrySet()) {
            LocalDate date = entry.getKey();
            int count = entry.getValue();
            long timestamp = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            data.add(Pair.of(timestamp, count));
        }

        // Train a linear regression model
        SimpleRegression regression = new SimpleRegression();
        for (Pair<Long, Integer> datum : data) {
            regression.addData(datum.getLeft(), datum.getRight());
        }

        // Predict the number of posts this month
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        long startOfMonthTimestamp = startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        long endOfMonthTimestamp = endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        double prediction = regression.predict(endOfMonthTimestamp + (endOfMonthTimestamp - startOfMonthTimestamp));

        return (int) Math.round(prediction);
    }



}

