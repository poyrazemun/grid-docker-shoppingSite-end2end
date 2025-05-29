package base;

import listeners.LoggingListener;
import listeners.RetryTransformer;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LandingPage;
import pages.ProductsPage;
import utils.ConfigReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Properties;

@Listeners({TestListener.class, LoggingListener.class, RetryTransformer.class})
public class BaseTest {
    protected WebDriver driver;
    public static LandingPage landingPage;
    protected ProductsPage productsPage;

    @BeforeSuite
    public void setEnvironment() {
        try {
            Properties props = new Properties();
            props.setProperty("Browser", ConfigReader.get("browser"));
            props.setProperty("Environment", "QA");
            props.setProperty("Platform", System.getProperty("os.name"));

            File file = new File("target/allure-results/environment.properties");
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            props.store(fos, "Environment Details");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void copyCategoriesJson() {
        try {
            Path src = Paths.get("src/test/resources/categories.json");
            Path dest = Paths.get("target/allure-results/categories.json");
            Files.createDirectories(dest.getParent());
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
