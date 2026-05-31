package task9_1P.projeect;

import java.util.List;

public class App {
	public static void main(String[] args) {

		TaskRepository repository =  new HardcodedTaskRepository();
		TaskInboxService service = new TaskInboxService(repository);
		System.out.println("============================================================");
		System.out.println("                      TASK INBOX                           ");
		System.out.println("============================================================");

		List<TaskSummary> inbox = service.getTaskInbox("student_01");
		for (TaskSummary t : inbox) {
			System.out.printf("ID: %-4s | Title: %-20s | Status: %-25s | Unread: %d%n", t.getTaskId(), t.getTitle(),
					((TaskSummary) t).getFeedbackStatus(), t.getUnreadCount());
		}
		for (TaskSummary t : inbox) {
			System.out.println("\n------------------------------------------------------------");
			System.out.println("TASK DETAIL — " + t.getTaskId() + " : " + t.getTitle());
			System.out.println("------------------------------------------------------------");

			TaskDetail detail = service.viewInboxTask(t.getTaskId());
			System.out.println("Feedback Status : " + detail.getFeedbackStatus());
			System.out.println("Messages        :");
			for (ChatMessage msg : detail.getMessages()) {
				System.out.println("  " + msg);
			}
		}
		System.out.println("\n============================================================");
		System.out.println("                       END OF INBOX                        ");
		System.out.println("============================================================");
	}
}
