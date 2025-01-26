package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.Progress;
import at.technikum.studymasterbackend.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {
    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public double getProgressByUserId(Long userId) {
        if (userId == null) throw new IllegalArgumentException("Missing userId");

        Progress progress = progressRepository.getProgressByUserId(userId);
        return progress != null ? progress.getProgress_percentage() : 0.0;
    }

    // TODO rework Progress -> Woran wird der Fortschritt gemessen? Was ist die maximale Punktzahl?
    public void addProgressPoints(Long userId, int points) {
        if(userId == null) throw new IllegalArgumentException("UserId must not be null");
        if(points < 0) throw new IllegalArgumentException("Points must be non-negative");

        Progress progress = progressRepository.getProgressByUserId(userId);
        if (progress == null) {
            progress = new Progress();
            progress.setUserId(userId);
            progress.setPoints_earned(points);
            progress.setProgress_percentage((double) points/100);
        } else {
            progress.setPoints_earned(points);
            progress.setProgress_percentage(progress.getProgress_percentage() + (double) points/100);
        }
        progressRepository.save(progress);
    }
}
