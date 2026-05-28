package web.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import web.MyServer;
import web.service.MathQuestionService;

public class TestMathQuestionService {
	
	private static final String CHROME_DRIVER_PATH = "C:\\Users\\NLS\\Desktop\\chromedriver-win64\\chromedriver.exe";
	private static WebDriver driver;
	
	private void sleep(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private WebDriver submitForm(String url2, String username, String password, String dob) {
		try {
			URL url = new URL(url2);
			driver.navigate().to(url.toString());
			WebElement ele = driver.findElement(By.id("username"));
			ele.clear();
			ele.sendKeys(username == null ? "" : username);
			ele = driver.findElement(By.id("passwd"));
			ele.clear();
			ele.sendKeys(password == null ? "" : password);
			ele = driver.findElement(By.id("dob"));
			LocalDate storedDob = null;
			storedDob = LocalDate.parse(dob);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			ele.sendKeys(storedDob == null ? "" : storedDob.format(formatter));
			ele = driver.findElement(By.cssSelector("[type=submit]"));
			sleep(5);
			ele.submit();
			
		} catch (TimeoutException e) {
			System.out.println("Warning: login-status element did not appear in time");
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return driver;
	}
	
	private WebDriver submitForm(String url2, String number1, String number2, Double results) {
		try {
			URL url = new URL(url2);
			driver.navigate().to(url.toString());
			WebElement ele = driver.findElement(By.id("number1"));
			ele.clear();
			ele.sendKeys(number1 == null ? "" : number1);
			ele = driver.findElement(By.id("number2"));
			ele.clear();
			ele.sendKeys(number2 == null ? "" : number2);
			ele = driver.findElement(By.id("result"));
			ele.sendKeys(results == null ? "" : String.valueOf(results));
			ele = driver.findElement(By.cssSelector("[type=submit]"));
			sleep(5);
			ele.submit();
			
		} catch (TimeoutException e) {
			System.out.println("Warning: login-status element did not appear in time");
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return driver;
	}
	@BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        MyServer.main(new String[]{});
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
    }
	

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testLoginSuccess() {
        submitForm("http://localhost:8080/login", "ahsan", "ahsan_pass", "2000-12-15");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q1"));
    }
    
    @Test
    public void testLoginFailed() {
        submitForm("http://localhost:8080/login", "ahsan", "ahsan_123", "2000-12-15");
        WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("Incorrect credentials.", actualMessage);
    }
	@Test
	public void testTrueAdd() {
		submitForm("http://localhost:8080/q1", "1.0", "2.0", 3.0);
		Assert.assertTrue(driver.getCurrentUrl().contains("/q2"));
	}
	
	@Test
    public void testQ1WrongAnswer() {
		submitForm("http://localhost:8080/q1", "1.0", "2.0", 4.0);
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("Wrong answer, try again.", actualMessage);
	}

	@Test
	public void testAddNumberEmpty() {
		driver.get("http://localhost:8080/q1");
        driver.findElement(By.cssSelector("[type='submit']")).click();
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("All fields are required.", actualMessage);
	}
	@Test
	public void testAddNumber1Empty() {
		submitForm("http://localhost:8080/q1", "", "2.0", 4.0);
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("All fields are required.", actualMessage);
	}
	@Test
	public void testAddNumber1String() {
		submitForm("http://localhost:8080/q1", "ABC", "XYZ", 4.0);
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("Only numeric values are allowed.", actualMessage);
	}
	
	@Test
    public void testQ2Success() {
		submitForm("http://localhost:8080/q2", "5.0", "2.0", 3.0);
		Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
    }
	@Test
	public void testAddNumber2String() {
		submitForm("http://localhost:8080/q2", "ABC", "XYZ", 4.0);
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("Only numeric values are allowed.", actualMessage);
	}
	@Test
    public void testQ2WrongAnswer() {
		submitForm("http://localhost:8080/q2", "7.0", "2.0", 4.0);
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("Wrong answer, try again.", actualMessage);
	}
	@Test
	public void testAddNumber2Empty() {
		submitForm("http://localhost:8080/q2", "", "2.0", 4.0);
		WebElement message = driver.findElement(By.id("message"));
		String actualMessage = message.getText();
		Assert.assertEquals("All fields are required.", actualMessage);
	}
}
