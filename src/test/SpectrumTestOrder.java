import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SpectrumTestOrder {

    private String chromeDriverPath = "lib\\chromedriver.exe";


    private WebDriver driver;
    private String addressSelector = "input[id^=address1]";
    private String zipSelector = "input[id^=zip]";
    private String submitBtnSelector = "button[type=submit][data-linkname=\"View All Offers\"]";
    private String address = "3824 Lindell Blvd";
    private String zip = "63108";
    private String offerButtonSelector = "button[id^=choose_autoBundleOffer]";
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

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://spectrum.com/");
        /* Get offers for address/zip */
        driver.findElement(By.cssSelector(addressSelector)).sendKeys(address);
        driver.findElement(By.cssSelector(zipSelector)).sendKeys(zip);
        driver.findElement(By.cssSelector(submitBtnSelector)).click();

        /* Wait until offers are loaded and click the first offer */
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement firstOffer = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(offerButtonSelector)));
        firstOffer.click();

        /* Wait until offer detailed information is loaded and then click the Continue button */
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(contiuneBtnSelector)));
        continueBtn.click();

        /* Wait until contact information is loaded and then fill in the information */
        wait.until(ExpectedConditions.elementToBeClickable(By.id(firstNameId)));
        driver.findElement(By.id(firstNameId)).sendKeys(firstNameInput);
        driver.findElement(By.id(lastNameId)).sendKeys(lastNameInput);
        driver.findElement(By.id(phoneNumberId)).sendKeys(phoneNumberInput);
        driver.findElement(By.id(emailId)).sendKeys(emailInput);
        driver.findElement(By.id(confirmEmailId)).sendKeys(confirmEmailInput);
    }

    @After
    public void cleanUp() {
//        driver.close();
    }
}
