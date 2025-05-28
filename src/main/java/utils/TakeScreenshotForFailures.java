package utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenshotForFailures {

    // Ekran görüntüsü alma metodu
    public static void takeScreenshot(WebDriver driver, String testName) {

        // Zaman damgası oluştur
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";

        // Ekran görüntüsü alma ve kaydetme
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "./screenshots/" + screenshotName;

        try {
            // Klasör kontrolü ve ekran görüntüsünü kaydet
            File screenshotDir = new File("./screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdir();
            }
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }

    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public static byte[] attachScreenshotToAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
