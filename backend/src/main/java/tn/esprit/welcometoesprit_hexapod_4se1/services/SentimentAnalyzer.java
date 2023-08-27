package tn.esprit.welcometoesprit_hexapod_4se1.services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Properties;
@Service
public class SentimentAnalyzer {

    public static SentimentResult analyzeSentiment(String text) {
        // Initialiser le pipeline NLP avec les annotateurs nécessaires
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Analyser le sentiment du texte avec le pipeline NLP
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        // Extraire la valeur de sentiment globale
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        double sentimentValue = 0.0;
        for (CoreMap sentence : sentences) {
            sentimentValue += getSentimentScore(sentence);
        }
        double averageSentiment = sentimentValue / sentences.size();

        // Renvoyer le résultat sous forme d'un objet SentimentResult
        return new SentimentResult(text, averageSentiment);
    }

    private static double getSentimentScore(CoreMap sentence) {
        // Récupérer l'arbre d'analyse de sentiment de la phrase
        Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

        // Calculer la valeur de sentiment à partir de l'arbre d'analyse
        int score = RNNCoreAnnotations.getPredictedClass(tree);
        double sentimentScore = (score - 2) / 2.0; // Transformer le score de [-2,2] à [-1,1]
        return sentimentScore;
    }

    public static class SentimentResult {
        private String text;
        private double sentiment;

        public SentimentResult(String text, double sentiment) {
            this.text = text;
            this.sentiment = sentiment;
        }
        public double getSentiment() {
            return sentiment;
        }


    }

}
