package task9_1P.projeect;

import java.util.Comparator;
import java.util.List;

public class TaskInboxService {
	
	public class TaskNotFoundException extends RuntimeException {
	    public TaskNotFoundException(String message) {
	        super(message);
	    }
	}

	
	
	private static final String ERR_STUDENT = "studentId must not be null or blank";
	private final TaskRepository repository;

	public TaskInboxService(TaskRepository repository) {
		this.repository = repository;
	}

	public List<TaskSummary> getTaskInbox(String studentId) {
		validate(studentId, ERR_STUDENT);
		List<TaskSummary> tasks = repository.findByStudentId(studentId);
		tasks.sort(Comparator.comparingInt(TaskSummary::getUnreadCount).reversed()
				.thenComparing(Comparator.comparing(TaskSummary::getSubmittedAt).reversed()));
		return tasks;
	}

	public TaskDetail viewInboxTask(String taskId) {
		validate(taskId, "taskId must not be null or blank");
		TaskDetail detail = repository.findDetailByTaskId(taskId);
		if (detail == null) {
			throw new TaskNotFoundException("Task not found: " + taskId);
		}
		return detail;
	}

	private void validate(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}

