package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.Subject;
import at.technikum.studymasterbackend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.createSubject(subject));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects(@RequestParam Long semesterId) {
        return ResponseEntity.ok(subjectService.getSubjectsBySemesterId(semesterId));
    }
}

