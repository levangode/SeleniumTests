package com.amind.tests

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Specification

class TestGroovy extends Specification {
    private WebDriver driver
    private boolean acceptNextAlert = true
    private StringBuffer verificationErrors = new StringBuffer();

    def "test search keyword Software"(){
        setup:
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Levan\\Desktop\\chromedriver_win32\\chromedriver.exe")
        driver = new ChromeDriver()
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

        when:
        driver.get("https://www.wikipedia.org/")
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='The Free Encyclopedia'])[1]/following::strong[1]")).click()
        driver.findElement(By.id("p-search")).click()
        driver.findElement(By.id("searchInput")).clear()
        driver.findElement(By.id("searchInput")).sendKeys("software")
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Mobile view'])[1]/following::span[1]")).click()

        then:
        driver.findElement(By.id("firstHeading")).text == "Sofatware"
        driver.findElement(By.id("siteSub")).text == "From Wikipedia, the free encyclopedia"

        cleanup:
        driver.close()
    }
}
