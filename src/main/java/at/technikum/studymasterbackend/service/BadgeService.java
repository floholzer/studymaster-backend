package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.Badge;
import at.technikum.studymasterbackend.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    // Alle Badges abrufen
    public List<Badge> getAllBadges() {
        return StreamSupport.stream(badgeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Badge getBadgeById(Long id) {
        return badgeRepository.findById(id).orElse(null);
    }

    public void deleteBadge(Long id) {
        badgeRepository.deleteById(id);
    }
}
