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
        DateUtil input = new DateUtil(31, 1, 2024);
        DateUtil expected = new DateUtil(1, 2, 2024);
        System.out.println("Before increment: " + input);
        input.increment();
        String actual = input.getDay() + "-" + input.getMonth() + "-" + input.getYear();
        String expectedStr = expected.getDay() + "-" + expected.getMonth() + "-" + expected.getYear();
        logTest("1B", "increment()", "31-1-2024", expectedStr, actual);
        Assert.assertEquals(expected.getDay(), input.getDay());
        Assert.assertEquals(expected.getMonth(), input.getMonth());
        Assert.assertEquals(expected.getYear(), input.getYear());
    }

    @Test
    public void testMaxJanuary31ShouldDecrementToJanuary30() {
        // January max boundary area: max-1
        DateUtil date = new DateUtil(31, 1, 2024);
        date.decrement();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("2B", "decrement()", "31-1-2024", "30-1-2024", actual);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(1, date.getMonth());
    }

    @Test
    public void testNominalJanuary() {
        int rand_day_1_to_31 = 1 + new Random().nextInt(31);
        DateUtil date = new DateUtil(rand_day_1_to_31, 1, 2024);
        String inputStr = rand_day_1_to_31 + "-1-2024";
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        int expectedDay = rand_day_1_to_31 == 31 ? 1 : rand_day_1_to_31 + 1;
        int expectedMonth = rand_day_1_to_31 == 31 ? 2 : 1;
        String expectedStr = expectedDay + "-" + expectedMonth + "-2024";
        logTest("NominalJan", "increment()", inputStr, expectedStr, actual);
    }

    @Test
    public void testMinJanuary1ShouldIncrementToJanuary2() {
        DateUtil date = new DateUtil(1, 1, 2024);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("3B", "increment()", "1-1-2024", "2-1-2024", actual);
        Assert.assertEquals(2, date.getDay());
        Assert.assertEquals(1, date.getMonth());
    }

    @Test
    public void testMinJanuary1ShouldDecrementToDecember31() {
        DateUtil date = new DateUtil(1, 1, 2024);
        date.decrement();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("4B", "decrement()", "1-1-2024", "31-12-2023", actual);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(2023, date.getYear());
    }

    @Test
    public void test1B_1June1994() {
        DateUtil date = new DateUtil(1, 6, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("1B", "increment()", "1-6-1994", "2-6-1994", actual);
        Assert.assertEquals(2, date.getDay());
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1994, date.getYear());
    }

    @Test
    public void test2B_2June1994() {
        DateUtil date = new DateUtil(2, 6, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("2B", "increment()", "2-6-1994", "3-6-1994", actual);
        Assert.assertEquals(3, date.getDay());
    }

    @Test
    public void test3B_14June1994() {
        DateUtil date = new DateUtil(14, 6, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("3B", "increment()", "14-6-1994", "15-6-1994", actual);
        Assert.assertEquals(15, date.getDay());
        Assert.assertEquals(6, date.getMonth());
    }

    @Test
    public void test4B_30June1994_shouldGoToJuly1() {
        DateUtil date = new DateUtil(30, 6, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("4B", "increment()", "30-6-1994", "1-7-1994", actual);
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(7, date.getMonth());
    }

    @Test(expected = RuntimeException.class)
    public void test5B_31June1994_invalidDate() {
        logTest("5B", "constructor", "31-6-1994", "RuntimeException", "RuntimeException");
        new DateUtil(31, 6, 1994);
    }

    @Test
    public void test6B_15Jan1994() {
        DateUtil date = new DateUtil(15, 1, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("6B", "increment()", "15-1-1994", "16-1-1994", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(1, date.getMonth());
    }

    @Test
    public void test7B_15Feb1994() {
        DateUtil date = new DateUtil(15, 2, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("7B", "increment()", "15-2-1994", "16-2-1994", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(2, date.getMonth());
    }

    @Test
    public void test8B_15Nov1994() {
        DateUtil date = new DateUtil(15, 11, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("8B", "increment()", "15-11-1994", "16-11-1994", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(11, date.getMonth());
    }

    @Test
    public void test9B_15Dec1994() {
        DateUtil date = new DateUtil(15, 12, 1994);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("9B", "increment()", "15-12-1994", "16-12-1994", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(12, date.getMonth());
    }

    @Test
    public void test10B_15June1700() {
        DateUtil date = new DateUtil(15, 6, 1700);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("10B", "increment()", "15-6-1700", "16-6-1700", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1700, date.getYear());
    }

    @Test
    public void test11B_15June1701() {
        DateUtil date = new DateUtil(15, 6, 1701);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("11B", "increment()", "15-6-1701", "16-6-1701", actual);
        Assert.assertEquals(16, date.getDay());
    }

    @Test
    public void test12B_15June2023() {
        DateUtil date = new DateUtil(15, 6, 2023);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("12B", "increment()", "15-6-2023", "16-6-2023", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(6, date.getMonth());
    }

    @Test
    public void test13B_15June2024() {
        DateUtil date = new DateUtil(15, 6, 2024);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("13B", "increment()", "15-6-2024", "16-6-2024", actual);
        Assert.assertEquals(16, date.getDay());
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testLeapYear_Feb28ToFeb29_2024() {
        DateUtil date = new DateUtil(28, 2, 2024);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("LeapYear1", "increment()", "28-2-2024", "29-2-2024", actual);
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(2, date.getMonth());
    }

    @Test
    public void testLeapYear_Feb29ToMarch1_2024() {
        DateUtil date = new DateUtil(29, 2, 2024);
        date.increment();
        String actual = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        logTest("LeapYear2", "increment()", "29-2-2024", "1-3-2024", actual);
        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(3, date.getMonth());
    }
}
