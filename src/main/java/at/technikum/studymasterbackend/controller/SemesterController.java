package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.Semester;
import at.technikum.studymasterbackend.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/semesters")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

//    @PostMapping
//    public ResponseEntity<Semester> createSemester(@RequestBody Semester semester) {
//        return ResponseEntity.ok(semesterService.createSemester(semester));
//    }

    @PostMapping
    public ResponseEntity<?> createSemester(@RequestBody Semester semester) {
        try {
            Semester createdSemester = semesterService.createSemester(semester);
            return ResponseEntity.ok(createdSemester);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } // Fehlerbehandlung für mehr als 6 Semester

    @GetMapping("/{userId}")
    public ResponseEntity<List<Semester>> getSemesters(@PathVariable Long userId) {
        return ResponseEntity.ok(semesterService.getSemestersByUserId(userId));
    }

    @GetMapping("/{userId}/status/{status}")
    public ResponseEntity<List<Semester>> getSemestersByStatus(@PathVariable Long userId, @PathVariable String status) {
        return ResponseEntity.ok(semesterService.getSemestersByUserIdAndStatus(userId, status));
    }

    @PutMapping("/semester/{id}") // Update Semester
    public ResponseEntity<Semester> updateSemester(@PathVariable Long id, @RequestBody Semester updatedSemester) {
        Optional<Semester> semester = semesterService.updateSemester(id, updatedSemester);
        return semester.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}") // Delete Semester
    public ResponseEntity<String> deleteSemester(@PathVariable Long id) {
        boolean isDeleted = semesterService.deleteSemester(id);
        if (isDeleted) {
            return ResponseEntity.ok("Semester deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Semester not found.");
    }


}

