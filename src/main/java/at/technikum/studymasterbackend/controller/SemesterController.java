package at.technikum.studymasterbackend.controller;

import at.technikum.studymasterbackend.model.Semester;
import at.technikum.studymasterbackend.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @PostMapping
    public ResponseEntity<Semester> createSemester(@RequestBody Semester semester) {
        return ResponseEntity.ok(semesterService.createSemester(semester));
    }

    @GetMapping
    public ResponseEntity<List<Semester>> getSemesters(@RequestParam Long userId) {
        return ResponseEntity.ok(semesterService.getSemestersByUserId(userId));
    }
}

