package at.technikum.studymasterbackend.service;

import at.technikum.studymasterbackend.model.*;
import at.technikum.studymasterbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;
    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            SubjectRepository subjectRepository,
            SemesterRepository semesterRepository,
            BadgeRepository badgeRepository,
            UserBadgeRepository userBadgeRepository) {
        this.taskRepository = taskRepository;
        this.subjectRepository = subjectRepository;
        this.semesterRepository = semesterRepository;
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
    }

    // Retrieve all tasks for a user
    public List<Task> getTasksByUserId(Long userId) {
        logger.info("Fetching tasks for user ID: {}", userId);
        return taskRepository.findByUserId(userId);
    }

    // Create a new task
    public Task createTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus("open");
        }
        if (task.getEcts() == null) {
            task.setEcts(BigDecimal.ZERO);
        }
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }

        Task savedTask = taskRepository.save(task);
        logger.info("Created task ID: {} for user ID: {}", savedTask.getId(), savedTask.getUserId());
        return savedTask;
    }

    // Update an existing task
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        logger.info("Updating task ID: {}", id);
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setEcts(updatedTask.getEcts());
            task.setPointsPerSubmission(updatedTask.getPointsPerSubmission());
            task.setTotalSubmissions(updatedTask.getTotalSubmissions());
            task.setCompletedSubmissions(updatedTask.getCompletedSubmissions());
            return taskRepository.save(task);
        });
    }

    // Delete a task
    public void deleteTask(Long id) {
        logger.info("Deleting task ID: {}", id);
        taskRepository.deleteById(id);
    }

    // Mark a task as completed and assign points
    public Optional<Task> markTaskAsCompleted(Long id, int pointsPerCompletion) {
        if (pointsPerCompletion < 0) {
            throw new IllegalArgumentException("Points per completion cannot be negative.");
        }

        return taskRepository.findById(id).map(task -> {
            task.setStatus("completed");
            task.setPointsEarned(task.getPointsEarned() + pointsPerCompletion);

            logger.info("Marking task ID: {} as completed. Total points earned: {}", id, task.getPointsEarned());

            // Handle badges
            // handleBadges(task); TODO fix this

            return taskRepository.save(task);
        });
    }

    private void handleBadges(Task task) {
        Long subjectId = task.getSubjectId();
        Long userId = task.getUserId();

        // Check subject completion for mini badge
        List<Task> subjectTasks = taskRepository.findBySubjectId(subjectId);
        if (subjectTasks.stream().allMatch(t -> "completed".equals(t.getStatus()))) {
            awardMiniBadge(subjectId);
        }

        // Check semester completion for big badge
        Long semesterId = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subject ID: " + subjectId))
                .getSemesterId();
        List<Subject> semesterSubjects = subjectRepository.findBySemesterId(semesterId);
        if (semesterSubjects.stream().allMatch(subject -> areAllTasksCompleted(subject.getId()))) {
            awardBigBadge(semesterId);
        }

        // Check program completion for ultra badge
        if (areAllSemestersCompleted(userId)) {
            awardUltraBadge(userId);
        }
    }

    private void awardMiniBadge(Long subjectId) {
        logger.info("Awarding mini badge for subject ID: {}", subjectId);
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject not found: " + subjectId));

        Badge miniBadge = createBadge("Mini Badge", "Completed subject: " + subject.getName(), "mini");
        saveBadgeForUser(subject.getUserId(), miniBadge);
    }

    private void awardBigBadge(Long semesterId) {
        logger.info("Awarding big badge for semester ID: {}", semesterId);
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new IllegalArgumentException("Semester not found: " + semesterId));

        Badge bigBadge = createBadge("Big Badge", "Completed semester: " + semester.getName(), "big");
        saveBadgeForUser(semester.getUserId(), bigBadge);
    }

    private void awardUltraBadge(Long userId) {
        logger.info("Awarding ultra badge for user ID: {}", userId);
        Badge ultraBadge = createBadge("Ultra Badge", "Completed entire study program", "ultra");
        saveBadgeForUser(userId, ultraBadge);
    }

    private Badge createBadge(String name, String description, String type) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setDescription(description);
        badge.setBadgeType(type);
        return badgeRepository.save(badge);
    }

    private void saveBadgeForUser(Long userId, Badge badge) {
        UserBadge userBadge = new UserBadge();
        userBadge.setUserId(userId);
        userBadge.setBadgeId(badge.getId());
        userBadgeRepository.save(userBadge);
        logger.info("Badge ID: {} awarded to user ID: {}", badge.getId(), userId);
    }

    private boolean areAllTasksCompleted(Long subjectId) {
        return taskRepository.findBySubjectId(subjectId).stream()
                .allMatch(task -> "completed".equals(task.getStatus()));
    }

    private boolean areAllSemestersCompleted(Long userId) {
        List<Semester> semesters = semesterRepository.findByUserId(userId);
        return semesters.stream().allMatch(semester -> {
            List<Subject> subjects = subjectRepository.findBySemesterId(semester.getId());
            return subjects.stream().allMatch(subject -> areAllTasksCompleted(subject.getId()));
        });
    }
}
