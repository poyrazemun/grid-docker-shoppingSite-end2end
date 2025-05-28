package listeners;

import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.TakeScreenshotForFailures;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = Driver.getDriver();
        String testName = result.getMethod().getMethodName();
        TakeScreenshotForFailures.takeScreenshot(driver, testName);
    }
}
