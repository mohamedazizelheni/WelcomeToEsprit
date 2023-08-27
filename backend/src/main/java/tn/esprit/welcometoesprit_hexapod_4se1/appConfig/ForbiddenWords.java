package tn.esprit.welcometoesprit_hexapod_4se1.appConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ForbiddenWords {
    private static final Set<String> WORDS = new HashSet<>(Arrays.asList("badword1", "badword2", "badword3"));
    public boolean checkForForbiddenWords(String input) {

        String[] words = input.split("\\s+");//9asem el jomla f tableau ki yal9a espace a kel s heki yany space w + yany ynajmou ykounou fama barcha
        for (String word : words) {
            if (WORDS.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String filter(String input) {
        String[] words = input.split("\\s+");
        StringBuilder filtered = new StringBuilder();

        for (String word : words) {
            if (WORDS.contains(word.toLowerCase())) {
                for (int i = 0; i < word.length(); i++) {
                    filtered.append("*");
                }
            } else {
                filtered.append(word);
            }
            filtered.append(" ");
        }

        return filtered.toString().trim();
    }
}
