package tn.esprit.welcometoesprit_hexapod_4se1.appConfig;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Configuration
public class NLPConfiguration {

    @Bean(name = "stanfordCoreNLP")
    public StanfordCoreNLP stanfordCoreNLP() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        return new StanfordCoreNLP(props);
    }
}