package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // Retrieve the progress of a user
    @GetMapping("/{userId}")
    public ResponseEntity<Double> getProgressByUserId(@PathVariable Long userId) {
        double progress = progressService.getProgressByUserId(userId);
        return ResponseEntity.ok(progress);
    }

}
