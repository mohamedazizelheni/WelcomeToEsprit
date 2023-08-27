package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String achievement, String id, int achievementId) {
    }
}
