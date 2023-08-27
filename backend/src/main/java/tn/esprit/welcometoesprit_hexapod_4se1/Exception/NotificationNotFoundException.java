package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException() {
    }

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
