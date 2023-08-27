package tn.esprit.welcometoesprit_hexapod_4se1.Exception;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(){

    }
    public RatingNotFoundException(String message) {
        super(message);
    }
}
