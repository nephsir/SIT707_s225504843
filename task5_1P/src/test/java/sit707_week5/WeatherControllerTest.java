package sit707_week5;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController weatherController;
    private static double[] temps;

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

    @BeforeClass
    public static void init() {
        weatherController = WeatherController.getInstance();
        int nHours = weatherController.getTotalHours();
        temps = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            temps[i] = weatherController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void cleanup() {
        weatherController.close();
    }

    @Test
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");
        double minTemperature = temps[0];
        for (double temp : temps) {
            if (temp < minTemperature) {
                minTemperature = temp;
            }
        }
        Assert.assertTrue(weatherController.getTemperatureMinFromCache() == minTemperature);
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");
        double maxTemperature = temps[0];
        for (double temp : temps) {
            if (temp > maxTemperature) {
                maxTemperature = temp;
            }
        }
        Assert.assertTrue(weatherController.getTemperatureMaxFromCache() == maxTemperature);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");
        double sumTemp = 0;
        for (double temp : temps) {
            sumTemp += temp;
        }
        double averageTemp = sumTemp / temps.length;
        Assert.assertTrue(weatherController.getTemperatureAverageFromCache() == averageTemp);
    }

    @Test
    public void testTemperaturePersist() {
        /*
		 * Remove below comments ONLY for 5.3C task.
         */
//		System.out.println("+++ testTemperaturePersist +++");
//		
//		// Initialise controller
//		WeatherController wController = WeatherController.getInstance();
//		
//		String persistTime = wController.persistTemperature(10, 19.5);
//		String now = new SimpleDateFormat("H:m:s").format(new Date());
//		System.out.println("Persist time: " + persistTime + ", now: " + now);
//		
//		Assert.assertTrue(persistTime.equals(now));
//		
//		wController.close();
    }
}
