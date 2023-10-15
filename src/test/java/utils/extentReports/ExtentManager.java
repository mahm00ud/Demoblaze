package utils.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tests.TestBase.timeStamp;

public class ExtentManager {

    public static final ExtentReports extentReports = new ExtentReports();
    public static String reportPath = "./extent-reports/extent-report"+timeStamp+".html" ;

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "Demoblaze Task");
        extentReports.setSystemInfo("Author", "Mahmoud");
        return extentReports;
    }

    }


