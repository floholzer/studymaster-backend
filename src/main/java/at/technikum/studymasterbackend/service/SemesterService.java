package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.Semester;
import at.technikum.studymasterbackend.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    public List<Semester> getSemestersByUserId(Long userId) {
        return semesterRepository.findByUserId(userId);
    }
}

