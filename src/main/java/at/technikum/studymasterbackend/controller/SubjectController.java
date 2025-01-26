package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.Subject;
import at.technikum.studymasterbackend.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        try {
            Subject createdSubject = subjectService.createSubject(subject);
            return ResponseEntity.ok(createdSubject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating subject: " + e.getMessage());
        }
    }

    @GetMapping("/{semesterId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable Long semesterId) {
        return ResponseEntity.ok(subjectService.getSubjectsBySemesterId(semesterId));
    }

    @PostMapping("/{subjectID}/complete")
    public ResponseEntity<String> markSubjectCompleted(@PathVariable Long subjectID) {
        if (subjectID == null) throw new IllegalArgumentException("Subject ID cannot be null");

        try {
            subjectService.markSubjectCompleted(subjectID);
            return ResponseEntity.status(HttpStatus.OK).body("Subject marked as completed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found");
        }
    }

    @PutMapping("/subjects/{id}") //Subject aktualisieren
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        Optional<Subject> subject = subjectService.updateSubject(id, updatedSubject);
        return subject.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/subjects/{id}") //Subject löschen
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        boolean isDeleted = subjectService.deleteSubject(id);
        if (isDeleted) {
            return ResponseEntity.ok("Subject deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subject not found.");
    }


}

