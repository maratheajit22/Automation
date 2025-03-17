package Reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentReportManager {
	  private static ExtentReports extent;
	  public static ExtentReports getReportInstance() {
	        if (extent == null) {
	            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);
	            extent.setSystemInfo("Tester", "QA Team");
	            extent.setSystemInfo("OS", "Windows 10");
	            extent.setSystemInfo("Browser", "Chrome");
	        }
	        return extent;
	    }
}
