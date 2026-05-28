package web.service;

import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.PrintWriter;

import web.MyServer;
import web.handler.LoginServlet;

public class LoginServiceTest {

	private static final String CHROME_DRIVER_PATH = "C:\\Users\\NLS\\Desktop\\chromedriver-win64\\chromedriver.exe";
	private static final String LOGIN_HTML_URL = "login.html";
	private WebDriver driver;
	private MyServer server;

	private void sleep(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private WebDriver submitForm(String username, String password, String dob) {
		try {
			URL url = getClass().getClassLoader().getResource("login.html");
			driver.navigate().to(url.toString());
			sleep(5);
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

			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("login-status")));
		} catch (TimeoutException e) {
			System.out.println("Warning: login-status element did not appear in time");
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format");
		}

		return driver;
	}

	private String elementText(String id) {
		try {
			return driver.findElement(By.id(id)).getText();
		} catch (Exception e) {
			return "";
		}
	}
	
	@Test
    public void testServerObjectCreated() {
		Assert.assertNotNull(server);
    }


	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		server = new MyServer();
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("tearDown: driver already closed — " + e.getMessage());
			}
			driver = null;
		}
	}

	@Test
	public void testLoginSuccess() {
		submitForm("ahsan", "ahsan_pass", "2000-01-15");
		System.out.println("Title: " + driver.getTitle());
		Assert.assertEquals("success", driver.getTitle());
		Assert.assertEquals("success", elementText("login-status"));
		Assert.assertEquals("Welcome, ahsan! You are now logged in.", elementText("status-message"));
		Assert.assertEquals("", elementText("error-reason"));
	}
	@Test
    public void testLoginFail_WrongPassword() {
        submitForm("ahsan", "wrong_password", "2000-01-15");
        String title = driver.getTitle();
        System.out.println("Title: " + title);
        Assert.assertEquals("fail", title);
        Assert.assertEquals("fail",               elementText("login-status"));
    }
	@Test
    public void testLoginFail_PasswordCaseMismatch() {
        submitForm("ahsan", "AHSAN_PASS", "2000-01-15");
        Assert.assertEquals("fail", driver.getTitle());
 
    }
	@Test
    public void testLoginFail_UnknownUser() {
        submitForm("nobody", "ahsan_pass", "2000-01-15");
        Assert.assertEquals("fail", driver.getTitle());
    }
	@Test
    public void testLoginFail_UsernameCaseMismatch() {
        submitForm("AHSAN", "ahsan_pass", "2000-01-15");
        Assert.assertEquals("fail", driver.getTitle());
    }
	@Test
	public void testDoPostSuccess() throws Exception {
	    LoginServlet servlet = new LoginServlet();
	    HttpServletRequest req = mock(HttpServletRequest.class);
	    HttpServletResponse resp = mock(HttpServletResponse.class);
	    when(req.getParameter("username")).thenReturn("ahsan");
	    when(req.getParameter("passwd")).thenReturn("ahsan_pass");
	    when(req.getParameter("dob")).thenReturn("2000-01-15");
	    StringWriter sw = new StringWriter();
	    PrintWriter writer = new PrintWriter(sw);
	    when(resp.getWriter()).thenReturn(writer);
	    servlet.doPost(req, resp);
	    verify(resp).setContentType("text/html");
	    verify(resp).setCharacterEncoding("UTF-8");
	    writer.flush();
	    String output = sw.toString();
	    Assert.assertTrue(output.contains("success"));
	    Assert.assertTrue(output.contains("id=\"login-status\""));
	    Assert.assertTrue(output.contains("Welcome, ahsan! You are now logged in."));
	    Assert.assertTrue(output.contains("<title>success</title>"));
	}

	@Test
	public void testDoPostFail() throws Exception {
	    LoginServlet servlet = new LoginServlet();
	    HttpServletRequest req = mock(HttpServletRequest.class);
	    HttpServletResponse resp = mock(HttpServletResponse.class);
	    when(req.getParameter("username")).thenReturn("ahsan");
	    when(req.getParameter("passwd")).thenReturn("wrong_password");
	    when(req.getParameter("dob")).thenReturn("2000-01-15");
	    StringWriter sw = new StringWriter();
	    PrintWriter writer = new PrintWriter(sw);
	    when(resp.getWriter()).thenReturn(writer);
	    servlet.doPost(req, resp);
	    verify(resp).setContentType("text/html");
	    verify(resp).setCharacterEncoding("UTF-8");
	    writer.flush();
	    String output = sw.toString();
	    Assert.assertTrue(output.contains("fail"));
	    Assert.assertTrue(output.contains("id=\"login-status\""));
	    Assert.assertTrue(output.contains("Login failed. Please check your credentials."));
	    Assert.assertTrue(output.contains("<title>fail</title>"));
	}
	
	@Test
	public void testDoGet() throws Exception {
	    LoginServlet servlet = spy(new LoginServlet());
	    HttpServletRequest req = mock(HttpServletRequest.class);
	    HttpServletResponse resp = mock(HttpServletResponse.class);
	    when(req.getParameter("username")).thenReturn("ahsan");
	    when(req.getParameter("passwd")).thenReturn("ahsan_pass");
	    when(req.getParameter("dob")).thenReturn("2000-01-15");
	    StringWriter sw = new StringWriter();
	    PrintWriter writer = new PrintWriter(sw);
	    when(resp.getWriter()).thenReturn(writer);
	    servlet.doGet(req, resp);
	    verify(servlet, times(1)).doPost(req, resp);
	    writer.flush();
	    String output = sw.toString();
	    Assert.assertTrue(output.contains("success"));
	    Assert.assertTrue(output.contains("Welcome, ahsan! You are now logged in."));
	}
    @Test
    public void testLogin_validCredentials_returnsTrue() {
    	Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "2000-01-15"));
    }
    @Test
    public void testLogin_nullUsername_returnsFalse() {
    	Assert.assertFalse(LoginService.login(null, "ahsan_pass", "2000-01-15"));
    }

    @Test
    public void testLogin_nullPassword_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", null, "2000-01-15"));
    }

    @Test
    public void testLogin_nullDob_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
    }

    @Test
    public void testLogin_allNull_returnsFalse() {
    	Assert.assertFalse(LoginService.login(null, null, null));
    }
    @Test
    public void testLogin_blankUsername_returnsFalse() {
    	Assert.assertFalse(LoginService.login("   ", "ahsan_pass", "2000-01-15"));
    }

    @Test
    public void testLogin_blankPassword_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "   ", "2000-01-15"));
    }

    @Test
    public void testLogin_blankDob_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "   "));
    }
    @Test
    public void testLogin_usernameTooLong_returnsFalse() {
        String longUsername = "a".repeat(LoginService.MAX_FIELD_LENGTH + 1);
        Assert.assertFalse(LoginService.login(longUsername, "ahsan_pass", "2000-01-15"));
    }
    @Test
    public void testLogin_passwordTooLong_returnsFalse() {
        String longPassword = "p".repeat(LoginService.MAX_FIELD_LENGTH + 1);
        Assert.assertFalse(LoginService.login("ahsan", longPassword, "2000-01-15"));
    }
    @Test
    public void testLogin_dobTooLong_returnsFalse() {
        String longDob = "2000-01-15" + "x".repeat(LoginService.MAX_FIELD_LENGTH);
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", longDob));
    }
    @Test
    public void testLogin_usernameExactlyMaxLength_returnsFalse() {
        String maxUsername = "a".repeat(LoginService.MAX_FIELD_LENGTH);
        Assert.assertFalse(LoginService.login(maxUsername, "ahsan_pass", "2000-01-15"));
    }
    @Test
    public void testLogin_invalidDobFormat_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "15-01-2000"));
    }

    @Test
    public void testLogin_dobAsText_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "not-a-date"));
    }
    @Test
    public void testLogin_dobSlashFormat_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "2000/01/15"));
    }
    @Test
    public void testLogin_wrongUsername_returnsFalse() {
    	Assert.assertFalse(LoginService.login("wronguser", "ahsan_pass", "2000-01-15"));
    }

    @Test
    public void testLogin_wrongPassword_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "wrong_pass", "2000-01-15"));
    }
    @Test
    public void testLogin_wrongDob_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "1999-12-31"));
    }
    @Test
    public void testLogin_wrongUsernameAndPassword_returnsFalse() {
    	Assert.assertFalse(LoginService.login("other", "other_pass", "2000-01-15"));
    }
    @Test
    public void testLogin_allWrong_returnsFalse() {
    	Assert.assertFalse(LoginService.login("other", "other_pass", "1990-05-20"));
    }
    @Test
    public void testLogin_usernameWrongCase_returnsFalse() {
    	Assert.assertFalse(LoginService.login("Ahsan", "ahsan_pass", "2000-01-15"));
    }
    @Test
    public void testLogin_passwordWrongCase_returnsFalse() {
    	Assert.assertFalse(LoginService.login("ahsan", "Ahsan_Pass", "2000-01-15"));
    }

}
