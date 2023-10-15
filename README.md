# Demoblaze project
This project applies POM(Page Object Model) and Data-Driven design patterns and ExtentReport is used to display a report after each run from testng.xml file.
Selenium WebDriver java is used along with TestNg as an assertiong Library and ExtentReport for Reporting.

Project Structure:
- Page Classes has the following:

1- Page classes are located in main/java/pages, and the page class is used to locate the page elements and do any actions needed within the page.
2- PageBase Class is used to declare the driver for page classes and to initlaize any object used in the page class, also it's used to implement any method that we use repetitively like waits and some actions for elements.

- For DataDriven

1- TestData logic to read from Excel file is located in test/java/utils/dataDriven/ExcelReader class, we can use getExcelValueByAttribute() method to get a value from the excel sheet by identifying the sheet name and the cellName on the left of cellData.
2- Excel file is located at main/java/resources

- For Test Cases

1- Test classes are located in test/java/tests
2- TestBase class is used to initialize the driver and preparing @AfterMethod, @BeforeMethod and any other annotation used across all tests.

To run tests:
Note: Any failed test cases will run again to be confident in the test run, and number of retries can be configured from Retry Class maxTry varialbe, it's located in test/java/utils.listeners.Retry

1- Open testng.xml file, right click and run and after the run a report will open with all test run details.

2- Instructions to help you run with testng.xml.

2.1 You can add test classes within <classes></classes> tag to identify the test classes you want to run.  
2.2 You can add test methods within <class></class> tag to identify the test methods you want to run.  
Ex:
			<classes>
				<class name="testCases.TestCases">
					<methods>
              <include name="verifySuccessfullRegisterAndLogin"/>
              <include name="verifyCategoriesHaveItems"/>
              <include name="verifyAddAndRemoveFromCart"/>
              <include name="verifySuccessfulCheckout"/>
					</methods>
				</class>
			</classes>
