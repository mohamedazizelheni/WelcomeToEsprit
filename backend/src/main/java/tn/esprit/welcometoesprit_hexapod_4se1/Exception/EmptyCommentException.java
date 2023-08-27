package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class EmptyCommentException extends RuntimeException {
    public EmptyCommentException() {
    }

    public EmptyCommentException(String message) {
        super(message);
    }
}
