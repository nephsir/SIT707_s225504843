package sit707_week4;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests functions in LoginForm.
 *
 * @author Ahsan Habib
 */
public class LoginFormTest {
    
    
    private void logTest(String testId, String operation, String input, String expected, String actual) {
        System.out.println("=================================");
        System.out.println("Test ID   : " + testId);
        System.out.println("Operation : " + operation);
        System.out.println("Input     : " + input);
        System.out.println("Expected  : " + expected);
        System.out.println("Actual    : " + actual);
        System.out.println("=================================");
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "s225504843";
        logTest("ST01", "Validate Student ID", "studentId=" + studentId, "Student ID should not be null", studentId);
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Nephat Komu Muriithi";
        logTest("ST02", "Validate Student Name", "studentName=" + studentName, "Student name should not be null", studentName);
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode() {
        LoginStatus status = LoginForm.login(null, null);
        logTest("R1", "Login", "username=null, password=null", "loginSuccess=false, errorMsg=Empty Username", status.toString());
        Assert.assertTrue(status.isLoginSuccess() == false);
    }

    /*
	 * Write more test functions below.
     */
 
    /* Empty Username = -  * Password = - */
    @Test
    public void testR1_EmptyUsernameEmptyPassword() {
        LoginStatus status = LoginForm.login(null, null);
        assertFalse(status.isLoginSuccess());
        logTest("R1", "Login", "username=null, password=null", "loginSuccess=false, errorMsg=Empty Username", status.toString());
        assertEquals("Empty Username", status.getErrorMsg());
    }

    /*Empty Username = - wrong Password = W*/
    @Test
    public void testR2_EmptyUsernameWrongPassword() {
        LoginStatus status = LoginForm.login(null, "xyz");
        assertFalse(status.isLoginSuccess());
        logTest("R2", "Login", "username=null, password=xyz", "loginSuccess=false, errorMsg=Empty Username", status.toString());
        assertEquals("Empty Username", status.getErrorMsg());
    }

    /* Emty Username = - Correct Password = C */
    @Test
    public void testR3_EmptyUsernameCorrectPassword() {
        LoginStatus status = LoginForm.login(null, "ahsan_pass");
        assertFalse(status.isLoginSuccess());
        logTest("R3", "Login", "username=null, password=ahsan_pass", "loginSuccess=false, errorMsg=Empty Username", status.toString());
        assertEquals("Empty Username", status.getErrorMsg());
    }

    /* Wrong Username = W empty Password = -*/
    @Test
    public void testR4_WrongUsernameEmptyPassword() {
        LoginStatus status = LoginForm.login("abc", null);
        assertFalse(status.isLoginSuccess());
        logTest("R4", "Login", "username=abc, password=null", "loginSuccess=false, errorMsg=Empty Password", status.toString());
        assertEquals("Empty Password", status.getErrorMsg());
    }

    /* Wrong Username = W Wrong Password = W */
    @Test
    public void testR5_WrongUsernameWrongPassword() {
        LoginStatus status = LoginForm.login("abc", "xyz");
        assertFalse(status.isLoginSuccess());
        logTest("R5", "Login", "username=abc, password=xyz", "loginSuccess=false, errorMsg=Credential mismatch", status.toString());
        assertEquals("Credential mismatch", status.getErrorMsg());
    }

    /* Wrong Username = W Correct Password = C*/
    @Test
    public void testR6_WrongUsernameCorrectPassword() {
        LoginStatus status = LoginForm.login("abc", "ahsan_pass");
        assertFalse(status.isLoginSuccess());
        logTest("R6", "Login", "username=abc, password=ahsan_pass", "loginSuccess=false, errorMsg=Credential mismatch", status.toString());
        assertEquals("Credential mismatch", status.getErrorMsg());
    }

    /* Correct Username = C Empty Password = - */
    @Test
    public void testR7_CorrectUsernameEmptyPassword() {
        LoginStatus status = LoginForm.login("ahsan", null);
        assertFalse(status.isLoginSuccess());
        logTest("R7", "Login", "username=ahsan, password=null", "loginSuccess=false, errorMsg=Empty Password", status.toString());
        assertEquals("Empty Password", status.getErrorMsg());
    }

    /* Correct Username = C Wrong Password = W */
    @Test
    public void testR8_CorrectUsernameWrongPassword() {
        LoginStatus status = LoginForm.login("ahsan", "xyz");
        assertFalse(status.isLoginSuccess());
        logTest("R8", "Login", "username=ahsan, password=xyz", "loginSuccess=false, errorMsg=Credential mismatch", status.toString());
        assertEquals("Credential mismatch", status.getErrorMsg());
    }

    /* Correct Username = C Correct Password = C Empty Validation Code = - */
    @Test
    public void testR9_EmptyValidationCode() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        assertTrue(status.isLoginSuccess());
        boolean result = LoginForm.validateCode(null);
        logTest("R9", "Validate Code", "validationCode=null", "false", String.valueOf(result));
        assertFalse(result);
    }

    /* Correct Username = C Correct Password = C Wrong Validation Code = W */
    @Test
    public void testR10_WrongValidationCode() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        assertTrue(status.isLoginSuccess());
        boolean result = LoginForm.validateCode("abcd");
        logTest("R10", "Validate Code", "validationCode=abcd", "false", String.valueOf(result));
        assertFalse(result);
    }

    /* Correct Username = C correct Password = C Validation Code = C */
    @Test
    public void testR11_CorrectValidationCode() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        assertTrue(status.isLoginSuccess());
        boolean result = LoginForm.validateCode("123456");
        logTest("R11", "Validate Code", "validationCode=123456", "true", String.valueOf(result));
        assertTrue(result);
    }

}
