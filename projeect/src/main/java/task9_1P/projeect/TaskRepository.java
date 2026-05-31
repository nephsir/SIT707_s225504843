package task9_1P.projeect;

import java.util.List;

public interface TaskRepository {
	 List<TaskSummary> findByStudentId(String studentId);
	 TaskDetail        findDetailByTaskId(String taskId);
}
