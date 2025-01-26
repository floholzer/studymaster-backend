package at.technikum.studymasterbackend.service;
import at.technikum.studymasterbackend.model.Task;
import at.technikum.studymasterbackend.model.Subject;
import at.technikum.studymasterbackend.repository.SubjectRepository;
import at.technikum.studymasterbackend.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TaskRepository taskRepository; // Für die Abfrage von Tasks
    @Autowired
    private ProgressService progressService;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectsBySemesterId(Long semesterId) {
        return subjectRepository.findBySemesterId(semesterId);
    }

    public List<Subject> getSubjectsBySemesterIdAndStatus(Long semesterId, String status) {
        return subjectRepository.findBySemesterIdAndStatus(semesterId, status);
    }

    public void markSubjectCompleted(Long subjectId) {
        if (subjectId == null) throw new IllegalArgumentException("Subject ID cannot be null");

        // Check if all tasks are completed
        boolean allTasksCompleted = taskRepository.findBySubjectId(subjectId)
                .stream()
                .allMatch(task -> "completed".equals(task.getStatus()));

        if (!allTasksCompleted) {
            return;
        }

        // Get and update subject
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        // Calculate points
        List<Task> tasks = taskRepository.findBySubjectId(subjectId);
        int totalPoints = tasks.stream().mapToInt(Task::getPointsPerSubmission).sum();
        int earnedPoints = tasks.stream().mapToInt(Task::getPointsEarned).sum();

        // Calculate award
        long percentage = totalPoints > 0 ? Math.round(earnedPoints * 100.0 / totalPoints) : 0;
        progressService.addProgressPoints(subject.getUserId(),(int)percentage);
        subject.setStatus("completed");

        subjectRepository.save(subject);
    }

    public Optional<Subject> updateSubject(Long id, Subject updatedSubject) {
        return subjectRepository.findById(id).map(existingSubject -> {
            existingSubject.setName(updatedSubject.getName());
            existingSubject.setSemesterId(updatedSubject.getSemesterId());
            existingSubject.setUserId(updatedSubject.getUserId());
            existingSubject.setStatus(updatedSubject.getStatus());
            existingSubject.setAward(updatedSubject.getAward());
            return subjectRepository.save(existingSubject);
        });
    }

    public boolean deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }


}

