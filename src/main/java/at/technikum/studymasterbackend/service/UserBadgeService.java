package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.UserBadge;
import at.technikum.studymasterbackend.repository.UserBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserBadgeService {
    private final UserBadgeRepository userBadgeRepository;

    @Autowired
    public UserBadgeService(UserBadgeRepository userBadgeRepository) {
        this.userBadgeRepository = userBadgeRepository;
    }

    // Alle vom Benutzer erhaltenen Badges abrufen
    public List<UserBadge> getUserBadges(Long userId) {
        return userBadgeRepository.findByUserId(userId);
    }

    public void assignBadgeToUser(Long userId, Long badgeId) {
        UserBadge userBadge = new UserBadge();
        userBadge.setUserId(userId);
        userBadge.setBadgeId(badgeId);
        userBadge.setAwarded_at(LocalDateTime.now());
        userBadgeRepository.save(userBadge);
    }
}
