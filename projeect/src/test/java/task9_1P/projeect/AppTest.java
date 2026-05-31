package task9_1P.projeect;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import task9_1P.projeect.TaskInboxService.TaskNotFoundException;

public class AppTest {

	private TaskInboxService service;

	@Before
	public void setUp() {
		service = new TaskInboxService(new HardcodedTaskRepository());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTaskInbox_nullStudentId_throwsException() {
		service.getTaskInbox(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTaskInbox_blankStudentId_throwsException() {
		service.getTaskInbox("   ");
	}

	@Test
	public void getTaskInbox_unknownStudent_returnsEmptyList() {
		List<TaskSummary> result = service.getTaskInbox("unknown_student");
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isEmpty());
	}

	@Test
	public void getTaskInbox_validStudent_firstItemHasHighestUnread() {
		List<TaskSummary> result = service.getTaskInbox("student_01");
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals("T2", result.get(0).getTaskId()); // 3 unread
	}

	@Test
	public void getTaskInbox_validStudent_lastItemHasLowestUnread() {
		List<TaskSummary> result = service.getTaskInbox("student_01");
		Assert.assertEquals("T1", result.get(result.size() - 1).getTaskId()); // 0 unread
	}

	@Test
	public void getTaskInbox_validStudent_returnsAllThreeTasks() {
		List<TaskSummary> result = service.getTaskInbox("student_01");
		Assert.assertEquals(3, result.size());
	}
	@Test(expected = IllegalArgumentException.class)
	public void viewInboxTask_nullTaskId_throwsException() {
		service.viewInboxTask(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void viewInboxTask_blankTaskId_throwsException() {
		service.viewInboxTask("  ");
	}
	@Test(expected = TaskNotFoundException.class)
	public void viewInboxTask_unknownTaskId_throwsNotFoundException() {
		service.viewInboxTask("NON_EXISTENT");
	}

	@Test
	public void viewInboxTask_validTaskId_returnsFeedbackStatus() {
		TaskDetail detail = service.viewInboxTask("T2");
		Assert.assertNotEquals("Feedback Provided", detail.getFeedbackStatus());
	}
	@Test
	public void viewInboxTask_validTaskId_returnsMessages() {
		TaskDetail detail = service.viewInboxTask("T2");
		Assert.assertEquals(2, detail.getMessages().size());
		Assert.assertEquals("tutor_01", detail.getMessages().get(0).getSenderId());
	}
	@Test
	public void viewInboxTask_T1_hasSingleMessage() {
		TaskDetail detail = service.viewInboxTask("T1");
		Assert.assertEquals(1, detail.getMessages().size());
	}

}
