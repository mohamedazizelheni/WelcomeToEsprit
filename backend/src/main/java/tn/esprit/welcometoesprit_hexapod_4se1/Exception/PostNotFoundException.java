package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
