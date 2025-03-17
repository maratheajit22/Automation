import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import Reports.ExtentReportManager;
public class SampleTestTest {
	  ExtentReports extent;
	    ExtentTest test;
	    @BeforeTest
	    public void setup() {
	        extent = ExtentReportManager.getReportInstance();
	    }
WebDriver driver=new ChromeDriver();
	    @Test
	    public void testPass() {
	        test = extent.createTest("Test Case 1 - Passed");
	        test.log(Status.INFO, "Test Execution Started");
	        Assert.assertTrue(true);
	        test.log(Status.PASS, "Test Case Passed Successfully");
	    }

	    @Test
	    public void testFail() {
	        test = extent.createTest("Test Case 2 - Failed");
	        test.log(Status.INFO, "Test Execution Started");
	        Assert.fail("Failing the test case intentionally");
	        test.log(Status.FAIL, "Test Case Failed");
	    }
	    
	    @Test
	    public void testFail2() {
	        test = extent.createTest("Test Case 3 - Failed");
	        test.log(Status.INFO, "Test Execution Started");
	        Assert.assertEquals(1, 0, "Enter the correct no");
	        test.log(Status.FAIL, "Test Case Failed");
	    }
	    
	    @Test
	    public void testFail4() {
	        test = extent.createTest("Test Case - Failing but marked as Passed");
	        test.log(Status.INFO, "Test Execution Started");
	        try {
	            Assert.assertTrue(false); // This fails but is not caught
	            test.log(Status.PASS, "Test case passed"); // This line still executes
	        } catch (Exception e) {
	            test.log(Status.FAIL, "Test case failed: " + e.getMessage()); // This won't execute if test is not caught
	        }
	    }

	    @AfterTest
	    public void tearDown() {
	        extent.flush();  // Generates the report
	    }
	    
	    @AfterMethod
	    public void attachScreenshotOnFailure(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
	        } else if (result.getStatus() == ITestResult.SKIP) {
	            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable().getMessage());
	        } else {
	            test.log(Status.PASS, "Test Passed");
	        }
	        
	       
	    }
	    
	   
}
