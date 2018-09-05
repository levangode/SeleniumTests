
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestJava {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
    	//driver = new ChromeDriver();
    	
        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.21.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://spectrum.com/");
        driver.findElement(By.cssSelector("input[id^=address1]")).sendKeys("S Grand Blvd");
        driver.findElement(By.cssSelector("input[id^=zip]")).sendKeys("63104");
        driver.findElement(By.cssSelector("button[type=submit][data-linkname=\"View All Offers\"]")).click();

    }

    @After
    public void cleanUp(){
//        driver.close();
    }
}
