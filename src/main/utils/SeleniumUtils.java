package utils;

import java.io.File;
import java.io.IOException;

import enums.OutcomeType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ShootingStrategy;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

import javax.imageio.ImageIO;

public class SeleniumUtils {

	@Deprecated
	public static void captureScreenShot(WebDriver ldriver, final String testCaseName) {
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {			
			StringBuilder logPathBuilder = new StringBuilder("logs/screenshots/");
			logPathBuilder.append(testCaseName);
			logPathBuilder.append("/");
			logPathBuilder.append(System.currentTimeMillis());
			logPathBuilder.append(".png");			
			FileUtils.copyFile(src, new File(logPathBuilder.toString()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}


	}

	public static void captureScreenShotWithAshotLibrary(WebDriver driver, final String testCaseName, OutcomeType type){
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver);
		try {
			StringBuilder logPathBuilder = new StringBuilder("logs/screenshots/");
			logPathBuilder.append(testCaseName);
			logPathBuilder.append("/");
			logPathBuilder.append(System.currentTimeMillis());
			logPathBuilder.append(".png");
			File yourFile = new File(logPathBuilder.toString());
			yourFile.getParentFile().mkdirs();
			ImageIO.write(screenshot.getImage(), "PNG", yourFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
