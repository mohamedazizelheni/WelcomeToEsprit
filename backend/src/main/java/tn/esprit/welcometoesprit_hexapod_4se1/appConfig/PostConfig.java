package tn.esprit.welcometoesprit_hexapod_4se1.appConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PostConfig {
    @Bean
    public ForbiddenWords forbiddenWords() {
        return new ForbiddenWords();
    }
}
