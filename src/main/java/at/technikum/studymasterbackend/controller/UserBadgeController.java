package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.UserBadge;
import at.technikum.studymasterbackend.service.UserBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_badges")
public class UserBadgeController {

    @Autowired
    private UserBadgeService userBadgeService;

    // Alle vom Benutzer erhaltenen Badges abrufen
    @GetMapping
    public ResponseEntity<List<UserBadge>> getUserBadges(@RequestParam Long userId) {
        List<UserBadge> userBadges = userBadgeService.getUserBadges(userId);
        return ResponseEntity.ok(userBadges);
    }
}
