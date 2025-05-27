package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LandingPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.TakeScreenshotForFailures;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    public static LandingPage landingPage;
    protected ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        driver = Driver.getDriver();
        driver.get(ConfigReader.get("url"));
        landingPage = new LandingPage();
        Test testAnnotation = method.getAnnotation(Test.class);
        String[] groups = testAnnotation.groups();
        if (Arrays.asList(groups).contains("loginRequired")) {
            productsPage = landingPage.login(ConfigReader.get("user"), ConfigReader.get("password"));
        }

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakeScreenshotForFailures.takeScreenshot(Driver.getDriver(), result.getName());
            Driver.quitDriver();
        }

        Driver.quitDriver();
    }

}
