package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;
import utils.ConfigReader;
import utils.TakeScreenshotForFailures;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    public static LandingPage landingPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = Driver.getDriver();
        String url = ConfigReader.get("url");
        driver.get(url);
        landingPage = new LandingPage();

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakeScreenshotForFailures.takeScreenshot(Driver.getDriver(), result.getName());
            Driver.quitDriver();
        }

        Driver.quitDriver();
    }


    //Getting DATA from JSON file
    public List<HashMap<String, String>> getJSONDataToMap(String filePath) {

        //reading JSON to String
        String jsonContent;
        try {
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //converting String to HashMap

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data;
        try {
            data = objectMapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return data;

    }

}
