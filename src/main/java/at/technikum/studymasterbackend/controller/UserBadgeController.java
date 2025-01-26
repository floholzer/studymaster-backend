package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.Badge;
import at.technikum.studymasterbackend.model.UserBadge;
import at.technikum.studymasterbackend.service.BadgeService;
import at.technikum.studymasterbackend.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user_badges")
public class UserBadgeController {

    @Autowired
    private UserBadgeService userBadgeService;

    @Autowired
    private BadgeService badgeService;

    // Alle vom Benutzer erhaltenen Badges abrufen
    @GetMapping("/{userId}")
    public ResponseEntity<List<Badge>> getUserBadges(@PathVariable Long userId) {
        List<UserBadge> userBadges = userBadgeService.getUserBadges(userId);
        List<Badge> badges = userBadges.stream()
                .map(userBadge -> badgeService.getBadgeById(userBadge.getBadgeId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(badges);
    }
}
