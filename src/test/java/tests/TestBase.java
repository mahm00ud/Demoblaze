package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.dataDriven.ExcelReader.getExcelValueByAttribute;
import static utils.extentReports.ExtentManager.reportPath;

public class TestBase {

    public WebDriver driver;
    public SoftAssert softAssert;
    public HomePage homePage;
    public CategoryPage categoryPage;
    public ProductPage productPage;
    public CartPage cartPage;

    public static LocalDateTime myDateObj;
    public static DateTimeFormatter myFormatObj;
    public static String timeStamp;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classSetup() {
        //driver = new ChromeDriver();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        softAssert = new SoftAssert();

        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(getExcelValueByAttribute("URLs", "HomeURL"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @BeforeSuite
    public void beforeSuite() {
        timeStamp =getTimeStamp();
    }

    @AfterSuite
    public void afterSuite() {
        Desktop desktop = Desktop.getDesktop();
        //let's try to open the report after running the test suite
        File file = new File(reportPath);

        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getTimeStamp() {
        myDateObj = LocalDateTime.now();
        myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }
}
