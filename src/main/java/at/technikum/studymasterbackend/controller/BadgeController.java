package at.technikum.studymasterbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import at.technikum.studymasterbackend.service.BadgeService;
import at.technikum.studymasterbackend.model.Badge;

import java.util.List;

@RestController
@RequestMapping("/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    // Alle verfügbaren Badges abrufen
    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeService.getAllBadges();
        return ResponseEntity.ok(badges);
    }
    @GetMapping("/{badgeId}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable Long badgeId) {
        if (badgeId == null) throw new IllegalArgumentException("Badge id cannot be null");
        Badge badge = badgeService.getBadgeById(badgeId);
        return ResponseEntity.ok(badge);
    }
}
