package com.amind.tests;

import com.amind.enums.OutcomeType;
import com.amind.utils.SeleniumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SpectrumTestOrder_2 {

	private Logger logger = LogManager.getLogger();

	private String testCaseName;

	/* Set the driver path on your local computer */
	private String chromeDriverPath = "lib/chromedriver.exe";
	private String chromeDriverPathLinux = "lib/chromedriver";
	private String fireFoxDriverPath = "lib/geckodriver.exe";

	private WebDriver driver;
	private String addressSelector = "input[id^=address1]";
	private String aptSelector = "input[id^=apt]";
	private String zipSelector = "input[id^=zip]";
	private String submitBtnSelector = "button[type=submit][data-linkname=\"View All Offers\"]";
	private String address = "160 Riverside Blvd";
	private String apt = "10f";
	private String zip = "10069";
	//SPECIAL FEATURED OFFER
	private String offerButtonSelector = ".col-md-2.bottom-controls.offer-button button[id^=choose_autoBundleOffer]";
	private String contiuneBtnSelector = "#customize-your-order > section > section > div > div.ng-scope > div.ng-scope.ng-isolate-scope > div.col-md-4.hidden-xs.hidden-sm.ng-isolate-scope > div > aside > div > div.continueCartBtn.ng-scope > a[id^=continue_btn_side_cart][role=button]";
	private String firstNameId = "firstname";
	private String lastNameId = "lastname";
	private String phoneNumberId = "phoneNumber";
	private String firstNameInput = "Levan";
	private String lastNameInput = "Goderdzishvili";
	private String phoneNumberInput = "593252073";
	private String emailId = "email";
	private String confirmEmailId = "confirm-email";
	private String emailInput = "levan.goderdzishvili@amindSolutions.com";
	private String confirmEmailInput = "levan.goderdzishvili@amindSolutions.com";
	private String mobilePhoneTypeId = "phone-type-mobile-label";
	private String overAYearOrUnder = "over_a_year_over_1_year";

	@Before
	public void setUp() throws Exception {
		// System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		// driver = new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", fireFoxDriverPath);
		driver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver", chromeDriverPathLinux);
		//driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		testCaseName = "TestCase_2_" + new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
	}

	@Test
	public void testUntitledTestCase() throws Exception {
		try {
			logger.info(testCaseName + " - Launching");
			driver.get("https://spectrum.com/");
			driver.manage().window().maximize();
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);

			/* Get offers for address/zip */
			driver.findElement(By.cssSelector(addressSelector)).sendKeys(address);
			driver.findElement(By.cssSelector(aptSelector)).sendKeys(apt);
			driver.findElement(By.cssSelector(zipSelector)).sendKeys(zip);
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);
			driver.findElement(By.cssSelector(submitBtnSelector)).click();

			/* Wait until offers are loaded and click the first offer */
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement firstOffer = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(offerButtonSelector)));
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);
			firstOffer.click();
			
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);
			/*
			 * Wait until offer detailed information is loaded and then click the Continue
			 * button
			 */
			WebElement continueBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(contiuneBtnSelector)));
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);
			continueBtn.click();

			/* Wait until contact information is loaded and then fill in the information */
			wait.until(ExpectedConditions.elementToBeClickable(By.id(firstNameId)));
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);
			
			driver.findElement(By.id(firstNameId)).sendKeys(firstNameInput);
			driver.findElement(By.id(lastNameId)).sendKeys(lastNameInput);
			driver.findElement(By.id(phoneNumberId)).sendKeys(phoneNumberInput);
			driver.findElement(By.id(emailId)).sendKeys(emailInput);
			driver.findElement(By.id(confirmEmailId)).sendKeys(confirmEmailInput);
			driver.findElement(By.id(mobilePhoneTypeId)).click();
			driver.findElement(By.id(overAYearOrUnder)).click();
			Select month = new Select(driver.findElement(By.name("selectedMonth")));
			month.selectByValue("4");
			Select date = new Select(driver.findElement(By.name("selectedDay")));
			date.selectByValue("6");
			Select year = new Select(driver.findElement(By.name("selectedYear")));
			year.selectByValue("10");
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.SUCCESS);
			logger.info(testCaseName + " - Completed Successfully");
		} catch (Exception e) {
			logger.error(testCaseName + " - Error");
			logger.error(e.getMessage());
			SeleniumUtils.captureScreenShotWithAshotLibrary(driver, testCaseName, OutcomeType.FAIL);
		}
	}

	@After
	public void cleanUp() {
		driver.close();
	}
}
