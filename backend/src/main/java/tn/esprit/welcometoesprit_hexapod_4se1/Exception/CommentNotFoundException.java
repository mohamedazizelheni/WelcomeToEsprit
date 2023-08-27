package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
    }
    public CommentNotFoundException(String message) {
        super(message);
    }
}
