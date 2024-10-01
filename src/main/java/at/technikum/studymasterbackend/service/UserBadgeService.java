package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.UserBadge;
import at.technikum.studymasterbackend.repository.UserBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<UserBadge> updateUserBadge(Long id, UserBadge userBadgeDetails) {
        return userBadgeRepository.findById(id).map(userBadge -> {
            userBadge.setBadgeId(userBadgeDetails.getBadgeId());
            userBadge.setUserId(userBadgeDetails.getUserId());
            userBadge.setAwarded_at(userBadgeDetails.getAwarded_at());
            return userBadgeRepository.save(userBadge);
        });
    }

    public void deleteUserBadge(Long id) {
        userBadgeRepository.deleteById(id);
    }
}
