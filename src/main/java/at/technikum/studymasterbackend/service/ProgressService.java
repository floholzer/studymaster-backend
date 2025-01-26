package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.Progress;
import at.technikum.studymasterbackend.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final UserBadgeService userBadgeService;

    @Autowired
    public ProgressService(ProgressRepository progressRepository, UserBadgeService userBadgeService) {
        this.progressRepository = progressRepository;
        this.userBadgeService = userBadgeService;
    }

    public double getProgressByUserId(Long userId) {
        if (userId == null) throw new IllegalArgumentException("Missing userId");

        Progress progress = progressRepository.getProgressByUserId(userId);

        return progress != null ? progress.getProgress_percentage() : 0.0;
    }

    public void addProgressPoints(Long userId, int points) {
        if(userId == null) throw new IllegalArgumentException("UserId must not be null");
        if(points < 0) throw new IllegalArgumentException("Points must be non-negative");

        Progress progress = progressRepository.getProgressByUserId(userId);
        if (progress == null) {
            progress = new Progress();
            progress.setUserId(userId);
            progress.setPoints_earned(points);
            progress.setProgress_percentage((double) points / 100);
        } else {
            progress.setPoints_earned(points);
            progress.setProgress_percentage(progress.getProgress_percentage() + (double) points / 100);
        }
        progressRepository.save(progress);

        if (progress.getPoints_earned() >= 10) {
            userBadgeService.assignBadgeToUser(userId, 1L);
        }
        if (progress.getPoints_earned() >= 50) {
            userBadgeService.assignBadgeToUser(userId, 2L);
        }
        if (progress.getPoints_earned() >= 100) {
            userBadgeService.assignBadgeToUser(userId, 3L);
        }
    }
}
