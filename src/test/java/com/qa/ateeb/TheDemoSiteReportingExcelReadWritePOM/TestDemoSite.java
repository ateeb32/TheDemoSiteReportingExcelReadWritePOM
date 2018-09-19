package com.qa.ateeb.TheDemoSiteReportingExcelReadWritePOM;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestDemoSite {

	WebDriver driver = null;

	public static ExtentReports report; // REPORTING
	public ExtentTest test; // REPORTING

	@BeforeClass
	public static void initial() {

		report = new ExtentReports(
				"C:\\Users\\Admin\\Desktop\\My Eclipse Workspace\\TheDemoSiteReportingExcelReadWritePOM\\Reports\\Report.html",
				true); // REPORTING

	}

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Testing/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void testDemoSite() throws InterruptedException, IOException {

		test = report.startTest("This is a login test"); // REPORTING

		FileInputStream file = new FileInputStream(Constants.pathTestData + Constants.fileTestData);
		XSSFWorkbook workbook = new XSSFWorkbook(file); // EXCEL
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		boolean passed = true; // To make it continue tests even if we come to a login unsuccessful

		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

			Cell username = sheet.getRow(i).getCell(0);
			Cell password = sheet.getRow(i).getCell(1); // EXCEL

			String user = username.getStringCellValue();
			String pass = password.getStringCellValue();

			driver.get(Constants.websiteURL);

			test.log(LogStatus.INFO, "Demo Site Opened"); // REPORTING

			DemoSiteLandingPage landingPage = PageFactory.initElements(driver, DemoSiteLandingPage.class); // Call all elements from DemoSiteLandingPage class
			DemoSiteAddUser addUserPage = PageFactory.initElements(driver, DemoSiteAddUser.class);
			DemoSiteLoginUser loginUserPage = PageFactory.initElements(driver, DemoSiteLoginUser.class);

			landingPage.hypAddUser();

			addUserPage.writeUserFieldAddUser(user);
			addUserPage.writePassFieldAddUser(pass);
			addUserPage.clickSaveAddUser();
			addUserPage.clickLoginPage();

			loginUserPage.writeUserFieldLogin(user);
			loginUserPage.writePassFieldLogin(pass);
			loginUserPage.clickTestLogin();

			// assertTrue("Login Unsuccessful! (assertTrue)",
			// loginUserPage.checkSuccessfulLogin());
			// assertEquals("Login Unsuccessful! (assertEquals)", "**Successful Login**",
			// loginUserPage.checkSuccessfulLoginString());

			test.log(LogStatus.INFO, "Login Test Running ..."); // REPORTING
			if (loginUserPage.checkSuccessfulLoginString().equals("**Successful Login**")) { // REPORTING

				test.log(LogStatus.PASS, "Successful Login Text Was Found");
				//System.out.println("true");

			}

			else { // REPORTING

				test.log(LogStatus.FAIL, "Successful Login Text Was NOT Found");
				passed = false; // To make it continue tests even if we come to a login unsuccessful
				//System.out.println("false");

			}

//			assertTrue("Login Unsuccessful! (assertTrue)", loginUserPage.checkSuccessfulLogin());
//			assertEquals("Login Unsuccessful! (assertEquals)", "**Successful Login**",
//					loginUserPage.checkSuccessfulLoginString());
			// report.endTest(test); //REPORTING

			// Thread.sleep(1000);
			// WebElement checkElement =
			// driver.findElement(By.xpath("//*[@id=\"b_context\"]/li[1]/div/div[1]/h2"));
			// assertEquals("Selenium", searchPage.heading.getText());

			String writeToExcelCheck = loginUserPage.checkSuccessfulLoginString();
			String expected = "**Successful Login**";

			if (!writeToExcelCheck.equals(expected)) {
				
				test.log(LogStatus.FAIL, "Demo Site Login Test");
				report.endTest(test);
				report.flush();
				Utils.ExcelUtils.setCellData("Fail", i, 2, sheet, workbook);
				
			} else {
				
				Utils.ExcelUtils.setCellData("Pass", i, 2, sheet, workbook);
				test.log(LogStatus.PASS, "Demo Site Login Test");
				
			}

			//assertEquals(expected, writeToExcelCheck);
			report.endTest(test);
		}
		
		assertTrue(passed); // To make it continue tests even if we come to a login unsuccessful
		//report.flush();

	}

	@After
	public void tearDown() throws InterruptedException {

		Thread.sleep(3000);
		driver.close();

	}

	@AfterClass
	public static void end() { // REPORTING

		report.flush();

	}
}
