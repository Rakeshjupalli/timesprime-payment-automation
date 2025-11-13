package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Captures screenshots for debugging and reporting
 * Saves screenshots with timestamps and test names
 */
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "screenshots";

    static {
        // Create screenshots directory if not exists
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Capture screenshot with timestamp
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + File.separator + fileName;
            
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            
            System.out.println("✅ Screenshot captured: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot on failure
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        System.out.println("\n📸 Capturing screenshot on failure...");
        return captureScreenshot(driver, testName + "_FAILURE");
    }

    /**
     * Capture screenshot on success
     */
    public static String captureScreenshotOnSuccess(WebDriver driver, String testName) {
        return captureScreenshot(driver, testName + "_SUCCESS");
    }

    /**
     * Get screenshot directory
     */
    public static String getScreenshotDir() {
        return new File(SCREENSHOT_DIR).getAbsolutePath();
    }

    /**
     * Clear all screenshots
     */
    public static void clearScreenshots() {
        File dir = new File(SCREENSHOT_DIR);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            System.out.println("✅ Screenshots cleared");
        }
    }

    /**
     * Count screenshots
     */
    public static int countScreenshots() {
        File dir = new File(SCREENSHOT_DIR);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            return files != null ? files.length : 0;
        }
        return 0;
    }
}
