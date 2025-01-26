package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.Semester;
import at.technikum.studymasterbackend.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

//    public Semester createSemester(Semester semester) {
//        return semesterRepository.save(semester);
//    }

    public Semester createSemester(Semester semester) {
        // Prüfen, ob der Benutzer bereits 6 Semester hat
        long semesterCount = semesterRepository.countByUserId(semester.getUserId());
        if (semesterCount >= 6) {
            throw new IllegalArgumentException("A user cannot have more than 6 semesters.");
        }
        return semesterRepository.save(semester);
    }

    public List<Semester> getSemestersByUserId(Long userId) {
        return semesterRepository.findByUserId(userId);
    }

    public List<Semester> getSemestersByUserIdAndStatus(Long userId, String status) {
        return semesterRepository.findByUserIdAndStatus(userId, status);
    }

    public Optional<Semester> updateSemester(Long id, Semester updatedSemester) {
        return semesterRepository.findById(id).map(existingSemester -> {
            existingSemester.setName(updatedSemester.getName());
            existingSemester.setStatus(updatedSemester.getStatus()); // ich glaube mehr muss man nicht anpassen können?
            return semesterRepository.save(existingSemester);
        });
    }

    public boolean deleteSemester(Long id) {
        if (semesterRepository.existsById(id)) {
            semesterRepository.deleteById(id);
            return true;
        }
        return false;
    }


}

