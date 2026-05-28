package sit707_tasks;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ahsan Habib
 */
public class DateUtilTest {

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
        logTest("Name", "student id", studentId, "Not null", studentId);
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Nephat Komu Muriithi";
        logTest("Name", "student id", studentName, "Not null", studentName);
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testMaxJanuary31ShouldIncrementToFebruary1() {
        // January max boundary area: max+1
        DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldIncrementToFebruary1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(1, date.getDay());
    }

    @Test
    public void testMaxJanuary31ShouldDecrementToJanuary30() {
        // January max boundary area: max-1
        DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldDecrementToJanuary30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(1, date.getMonth());
    }

    @Test
    public void testNominalJanuary() {
        int rand_day_1_to_31 = 1 + new Random().nextInt(31);
        DateUtil date = new DateUtil(rand_day_1_to_31, 1, 2024);
        System.out.println("testJanuaryNominal > " + date);
        date.increment();
        System.out.println(date);
    } 
    // =====================
    // D1: 1–28 (safe range)
    // =====================

    @Test
    public void testD1_validRange() {
        DateUtil date = new DateUtil(15, 6, 2023);
        date.increment();
        Assert.assertEquals(16, date.getDay());
    }

    // =====================
    // D2: 29 (boundary case)
    // =====================
    @Test
    public void testD2_febLeapYear() {
        DateUtil date = new DateUtil(29, 2, 2024);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
    }

    // =====================
    // D3: 30th day
    // =====================
    @Test
    public void testD3_thirtyDayMonth() {
        DateUtil date = new DateUtil(30, 6, 2023);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(7, date.getMonth());
    }

    // =====================
    // D4: 31st day
    // =====================
    @Test
    public void testD4_thirtyOneDayMonth() {
        DateUtil date = new DateUtil(31, 1, 2023);
        date.increment();
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(2, date.getMonth());
    }

    // =====================
    // Invalid Day class
    // =====================
    @Test(expected = RuntimeException.class)
    public void testInvalidDay() {
        new DateUtil(32, 6, 2023);
    }

    // =====================
    // M2: 30-day months
    // =====================
    @Test
    public void testMonth30DayBoundary() {
        DateUtil date = new DateUtil(30, 4, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(5, date.getMonth());
    }

    // =====================
    // M3: February non-leap
    // =====================
    @Test
    public void testFebruaryNonLeap() {
        DateUtil date = new DateUtil(28, 2, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
    }

    // =====================
    // Leap year check
    // =====================
    @Test
    public void testLeapYearLogic() {
        Assert.assertTrue(DateUtil.isLeapYear(2024));
    }

    // =====================
    // Year boundary
    // =====================
    @Test
    public void testYearTransition() {
        DateUtil date = new DateUtil(31, 12, 2023);
        date.increment();

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    /*
	 * Complete below test cases.
     */
    @Test
    public void testMinJanuary1ShouldIncrementToJanuary2() {
        // Code here
    }

    @Test
    public void testMinJanuary1ShouldDecrementToDecember31() {
        // Code here
    }

    /*
	 * Write tests for rest months of year 2024.
     */
}
