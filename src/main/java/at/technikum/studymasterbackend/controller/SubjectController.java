package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.Subject;
import at.technikum.studymasterbackend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

//    @PostMapping
//    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
//        return ResponseEntity.ok(subjectService.createSubject(subject));
//    }
@PostMapping
public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
    try {
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.ok(createdSubject);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error creating subject: " + e.getMessage());
    }
} //Fehlerbehandlung für createSubject

    @GetMapping("/{semesterId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable Long semesterId) {
        return ResponseEntity.ok(subjectService.getSubjectsBySemesterId(semesterId));
    }

    @GetMapping("/{semesterId}/status/{status}")
    public ResponseEntity<List<Subject>> getSubjectsByStatus(@PathVariable Long semesterId, @PathVariable String status) {
        return ResponseEntity.ok(subjectService.getSubjectsBySemesterIdAndStatus(semesterId, status));
    }

    @GetMapping("/award/{award}")
    public ResponseEntity<List<Subject>> getSubjectsByAward(@PathVariable String award) {
        return ResponseEntity.ok(subjectService.getSubjectsByAward(award)); //Subjects nach Auszeichnung filtern
    }

    @GetMapping("/{semesterId}/awards")
    public ResponseEntity<List<String>> getAwardsForSemester(@PathVariable Long semesterId) {
        List<String> awards = subjectService.getSubjectsBySemesterId(semesterId)
                .stream()
                .map(Subject::getAward)
                .collect(Collectors.toList());
        return ResponseEntity.ok(awards);
    } //Auszeichnungen für alle Fächer eines Semesters abrufen


}

