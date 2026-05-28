package sit707_week5;

import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import java.text.SimpleDateFormat;

public class WeatherControllerTest {

    @Test
    public void testStudentIdentity() {
        String studentId = "s225504843";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Nephat Komu Muriithi";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperaturePersist() {
        /*
		 * Remove below comments ONLY for 5.3C task.
         */
        System.out.println("+++ testTemperaturePersist +++");

        // Initialise controller
        WeatherController wController = WeatherController.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("H:m:s");

        // Record before, call method, record after
        long beforeMs = System.currentTimeMillis();
        String persistTime = wController.persistTemperature(10, 19.5);
        long afterMs = System.currentTimeMillis();

        System.out.println("Persist time: " + persistTime
                + ", now: " + sdf.format(new Date(afterMs)));

        // Build every valid "H:m:s" second-tick in the window [before, after]
        java.util.Set<String> validTimes = new java.util.HashSet<>();
        for (long t = beforeMs; t <= afterMs + 1000; t += 1000) {
            // Truncate to second boundary
            long secondTs = (t / 1000) * 1000;
            validTimes.add(sdf.format(new Date(secondTs)));
        }

        System.out.println("Valid window: " + validTimes
                + ", persistTime: " + persistTime);

        Assert.assertTrue(
                "Expected persistTime '" + persistTime
                + "' to be within window " + validTimes,
                validTimes.contains(persistTime));

        wController.close();
    }
}
