package base;

import listeners.LoggingListener;
import listeners.RetryTransformer;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductsPage;
import utils.ConfigReader;

import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners({TestListener.class, LoggingListener.class, RetryTransformer.class})
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
        Driver.quitDriver();
    }

}
