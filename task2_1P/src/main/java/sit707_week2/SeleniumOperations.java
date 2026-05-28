package sit707_week2;

import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class demonstrates Selenium locator APIs to identify HTML elements.
 *
 * Details in Selenium documentation
 * https://www.selenium.dev/documentation/webdriver/elements/locators/
 *
 * @author Ahsan Habib
 */
public class SeleniumOperations {

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void localFile(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\NLS\\Desktop\\chromedriver-win64\\chromedriver.exe");
        System.out.println("Fire up chrome browser.");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        System.out.println("Driver info: " + driver);
        sleep(2);
        driver.get(url);
        System.out.println("Form ready. Page title: " + driver.getTitle());
        try {
            driver.findElement(By.xpath("//input[@name='gender' and @value='m']")).click();
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            for (WebElement e : inputs) {
                System.out.println("ID: " + e.getAttribute("id"));
                String id = e.getAttribute("id");
                if (id == null) {
                    continue;
                }
                switch (id) {
                    case "fname":
                        e.clear();
                        e.sendKeys("Khan");
                        break;
                    case "lname":
                        e.clear();
                        e.sendKeys("1234567899");
                        break;

                    case "checkbox":
                        if (!e.isSelected()) {
                            e.click();
                        }
                        break;
                }
                System.out.println("Filled field: " + id);
            }
            sleep(10);

            driver.findElement(By.cssSelector("input[type='submit']")).click();

        } catch (Exception e) {
            System.out.println("Error");
        }
        sleep(10);
    }

    public static void officeworks_registration_page(String url) {
        // Step 1: Locate chrome driver folder in the local drive.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\NLS\\Desktop\\chromedriver-win64\\chromedriver.exe");

        // Step 2: Use above chrome driver to open up a chromium browser.
        System.out.println("Fire up chrome browser.");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        System.out.println("Driver info: " + driver);

        sleep(2);

        driver.get("https://www.officeworks.com.au");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 300)");
        sleep(2);

        System.out.println("Navigating to registration page: " + url);
        // Load a webpage in chromium browser.
        driver.get(url);
        System.out.println("Waiting for registration form to render...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstname")));
        System.out.println("Form ready. Page title: " + driver.getTitle());

        /*
		 * How to identify a HTML input field -
		 * Step 1: Inspect the webpage, 
		 * Step 2: locate the input field, 
		 * Step 3: Find out how to identify it, by id/name/...
         */
        //inspecting the page
        try {
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            for (WebElement e : inputs) {
                System.out.println("ID: " + e.getAttribute("id"));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        // Find first input field which is firstname
        List<WebElement> elements = driver.findElements(By.id("firstname"));
        if (elements.size() > 0) {
            WebElement firstName = elements.get(0);
            firstName.clear();
            firstName.sendKeys("Ahsan");
            System.out.println("Firstname entered");
        } else {
            System.out.println("firstname field NOT found");
        }
        /*
		 * Find following input fields and populate with values
         */

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        for (WebElement e : inputs) {

            String id = e.getAttribute("id");
            if (id == null) {
                continue;
            }
            switch (id) {
                case "lastname":
                    e.sendKeys("Khan");
                    break;
                case "phoneNumber":
                    e.sendKeys("0112345678");
                    break;
                case "email":
                    e.sendKeys("ahsan@testmail.com");
                    break;
                case "password":
                    e.sendKeys("Test@12345");
                    break;
                case "confirmPassword":
                    e.sendKeys("Test@1245");
                    break;
            }

            System.out.println("Filled field: " + id);
        }
        // Write code
        /*
		 * Identify button 'Create account' and click to submit using Selenium API.
         */
        // Write code

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (WebElement btn : buttons) {
            String text = btn.getText().trim().toLowerCase();
            if (text.contains("create account")) {
                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].scrollIntoView(true);",
                                btn
                        );
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                System.out.println("Create account button clicked");
                break;
            }
        }
        /*
       
		 * Take screenshot using selenium API.
         */
        // Write code
        // Sleep a while
        sleep(10);

        // close chrome driver
        //driver.close();
    }

}
