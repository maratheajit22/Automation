import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utility.ScreenshotUtility;

public class TestWithExtentReports {
    
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setupExtent() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testPass() {
        test = extent.createTest("Test Case - Passed");
        driver.get("https://www.google.com");
        test.log(Status.PASS, "Navigated to Google");
    }

    @Test
    public void testFail() {
        test = extent.createTest("Test Case - Failed");
        driver.get("https://www.google.com");
        test.log(Status.INFO, "Navigated to Google");
        // Intentional failure
        assert false : "Failing test for demo";
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
            test.addScreenCaptureFromPath(screenshotPath);
        } else {
            test.log(Status.PASS, "Test Passed Successfully");
        }
        driver.quit();
    }

    @AfterTest
    public void flushReport() {
        extent.flush(); // Generate the final report
    }
}

