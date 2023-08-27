package tn.esprit.welcometoesprit_hexapod_4se1.services;

import java.io.InputStream;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class NLPService {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    public String answerQuestion(String question) {
        // Create a new Annotation object
        Annotation annotation = new Annotation(question);

        // Run the stanfordCoreNLP pipeline on the annotation
        stanfordCoreNLP.annotate(annotation);

        // Get the list of sentences from the annotation
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        // If there is at least one sentence
        if (!sentences.isEmpty()) {
            // Get the first sentence
            CoreMap sentence = sentences.get(0);

            // Get the list of tokens from the sentence
            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);

            // Create a new ArrayList to store the lemma of each token
            List<String> lemmas = new ArrayList<String>();

            // Loop through the tokens and add their lemma to the list
            for (CoreLabel token : tokens) {
                lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
            }

            // Join the lemmas together into a single string
            String lemmaString = String.join(" ", lemmas);

            // If the lemmaString contains the word "club"
            if (lemmaString.contains("club")) {
                return "club";
            }
            // Si la chaîne de caractères contient le mot "admission", retourner "admission"
            if (lemmaString.contains("admission")) {
                return "admission";
            }
// Si la chaîne de caractères contient les mots "bal" et "projet", retourner "bal de projet"
            if (lemmaString.contains("bal") || lemmaString.contains("projet")) {
                return "bal";
            }
            if (lemmaString.contains("parking") ) {
                return "parking";
            }
        }

        // If we haven't returned a result yet, return null
        return "notfound" ;
    }
}

