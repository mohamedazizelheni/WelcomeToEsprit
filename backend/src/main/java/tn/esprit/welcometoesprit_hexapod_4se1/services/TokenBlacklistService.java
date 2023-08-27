package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.BlacklistedToken;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.BlacklistedTokenRepository;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

import static tn.esprit.welcometoesprit_hexapod_4se1.security.SecurityConstants.EXPIRATION_TIME;

@Service
public class TokenBlacklistService {
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    public TokenBlacklistService(BlacklistedTokenRepository blacklistedTokenRepository) {
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

    public void addToBlacklist(String token) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, (int) EXPIRATION_TIME);
        Date removingTime = calendar.getTime();
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setRemovingTime(removingTime);
        blacklistedTokenRepository.save(blacklistedToken);
    }
    @Transactional
    @Scheduled(fixedDelay = 86400000) // runs every day
    public void removeExpiredTokensFromBlacklist() {
        Date removingTime = new Date();
        blacklistedTokenRepository.deleteAllByRemovingTimeBefore(removingTime);
    }
}
