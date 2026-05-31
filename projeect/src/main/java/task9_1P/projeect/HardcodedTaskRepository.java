package task9_1P.projeect;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardcodedTaskRepository implements TaskRepository {

	private static final Map<String, List<TaskSummary>> TASK_STORE = new HashMap<>();
	private static final Map<String, TaskDetail> DETAIL_STORE = new HashMap<>();

	static {
		TASK_STORE.put("student_01",
				List.of(new TaskSummary("T1", "Task 1.1P", "SIT223", LocalDateTime.now().minusDays(5), "In Review", 0),
						new TaskSummary("T2", "Task 2.1P", "SIT223", LocalDateTime.now().minusDays(2),
								"Feedback Provided", 3),
						new TaskSummary("T3", "Task 3.1P", "SIT223", LocalDateTime.now().minusDays(1),
								"Resubmission Required", 1)));
		DETAIL_STORE.put("T2",
				new TaskDetail("T2", "Feedback Provided",
						List.of(new ChatMessage("tutor_01", "Good attempt, please add more detail to section 2.",
								LocalDateTime.now().minusHours(5)),
								new ChatMessage("student_01", "Thank you, I have updated section 2.",
										LocalDateTime.now().minusHours(2)))));
		DETAIL_STORE.put("T3",
				new TaskDetail("T3", "Resubmission Required",
						List.of(new ChatMessage("tutor_01", "Please resubmit with the corrections outlined.",
								LocalDateTime.now().minusHours(10)),
								new ChatMessage("student_01", "Understood, I will resubmit by tomorrow.",
										LocalDateTime.now().minusHours(8)))));
		DETAIL_STORE.put("T1", new TaskDetail("T1", "In Review", List.of(new ChatMessage("student_01",
				"Submitted for review, please let me know if changes are needed.", LocalDateTime.now().minusDays(5)))));
	}

	@Override
	public List<TaskSummary> findByStudentId(String studentId) {
		return new ArrayList<>(TASK_STORE.getOrDefault(studentId, Collections.emptyList()));
	}

	@Override
	public TaskDetail findDetailByTaskId(String taskId) {
		return DETAIL_STORE.get(taskId);
	}

}
