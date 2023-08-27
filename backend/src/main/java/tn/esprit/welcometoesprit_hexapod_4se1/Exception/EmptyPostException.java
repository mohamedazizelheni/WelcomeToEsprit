package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class EmptyPostException extends RuntimeException {
    public EmptyPostException() {
    }

    public EmptyPostException(String message) {
        super(message);
    }
}
