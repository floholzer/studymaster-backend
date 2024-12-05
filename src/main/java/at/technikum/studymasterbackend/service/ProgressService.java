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
        Progress progress = progressRepository.getProgressByUserId(userId);
        return progress != null ? progress.getProgress_percentage() : 0.0;
    }
}
