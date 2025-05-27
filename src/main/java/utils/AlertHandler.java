package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHandler {

    private static final Logger logger = LogManager.getLogger(AlertHandler.class);

    public static void handleOptionalAlert(WebDriver driver){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            logger.info("Alert detected: {}", alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            logger.info("No alert!");
        }
    }


}
