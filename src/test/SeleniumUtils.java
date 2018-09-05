package test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class SeleniumUtils {
	public static void captureScreenShot(WebDriver ldriver, final String testCaseName) {
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {			
			StringBuilder logPathBuilder = new StringBuilder("log\\screenshots\\");
			logPathBuilder.append(testCaseName);
			logPathBuilder.append("\\");
			logPathBuilder.append(System.currentTimeMillis());
			logPathBuilder.append(".png");			
			FileUtils.copyFile(src, new File(logPathBuilder.toString()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
