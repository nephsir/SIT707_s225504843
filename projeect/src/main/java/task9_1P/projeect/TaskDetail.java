package task9_1P.projeect;

import java.util.Collections;
import java.util.List;

public class TaskDetail {
	
	private final String taskId;
	private final String feedbackStatus; 
	private final List<ChatMessage> messages;

	public TaskDetail(String taskId, String feedbackStatus, List<ChatMessage> messages) {
		this.taskId = taskId; 
		this.feedbackStatus = feedbackStatus;
		this.messages = Collections.unmodifiableList(messages);
	}

	public String getTaskId() { 
		return taskId; 
	}
	public String getFeedbackStatus(){
		return feedbackStatus; 
	} 
	public List<ChatMessage> getMessages() { 
			return messages; 
	}

}
