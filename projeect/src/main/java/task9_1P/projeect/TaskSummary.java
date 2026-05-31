package task9_1P.projeect;

import java.time.LocalDateTime;

public class TaskSummary {
	private final String taskId;
	private final String title;
	private final String unitCode;
	private final LocalDateTime submittedAt;
	private final String feedbackStatus;
	private final int unreadCount;

	public TaskSummary(String taskId, String title, String unitCode, LocalDateTime submittedAt, String feedbackStatus,
			int unreadCount) {
		this.taskId = taskId;
		this.title = title;
		this.unitCode = unitCode;
		this.submittedAt = submittedAt;
		this.feedbackStatus = feedbackStatus;
		this.unreadCount = unreadCount;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getTitle() {
		return title;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public int getUnreadCount() {
		return unreadCount;
	}
}
