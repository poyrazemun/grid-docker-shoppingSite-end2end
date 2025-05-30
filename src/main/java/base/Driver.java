package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigReader;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(Driver.class);


    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {

            String browser = System.getProperty("browser") != null
                    ? System.getProperty("browser")
                    : ConfigReader.get("browser").toLowerCase();

            boolean isRemote = Boolean.parseBoolean(System.getProperty("remote", "false"));
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

            logger.info("Browser: {}, Remote: {}, Headless: {}", browser, isRemote, isHeadless);

            try {
                if (isRemote) {
                    URL gridUrl;
                    try {
                        gridUrl = new URI(System.getProperty("grid.url", "http://localhost:4444/wd/hub")).toURL();
                    } catch (URISyntaxException | java.net.MalformedURLException e) {
                        throw new RuntimeException("Invalid Selenium Grid URL", e);
                    }
                    switch (browser) {
                        case "firefox":
                            FirefoxOptions firefoxOptions = new FirefoxOptions();
                            if (isHeadless) {
                                firefoxOptions.addArguments("-headless");
                                firefoxOptions.addArguments("--width=1920");
                                firefoxOptions.addArguments("--height=1080");
                            }
                            driver.set(new RemoteWebDriver(gridUrl, firefoxOptions));
                            break;

                        case "chrome":
                            ChromeOptions chromeOptions = new ChromeOptions();
                            chromeOptions.addArguments("--incognito");
                            if (isHeadless) {
                                chromeOptions.addArguments("--headless");
                                chromeOptions.addArguments("--window-size=1920,1080");
                            }
                            driver.set(new RemoteWebDriver(gridUrl, chromeOptions));
                            break;

                        case "edge":
                            EdgeOptions edgeOptions = new EdgeOptions();
                            if (isHeadless) {
                                edgeOptions.addArguments("--headless");
                                edgeOptions.addArguments("--window-size=1920,1080");
                            }
                            driver.set(new RemoteWebDriver(gridUrl, edgeOptions));
                            break;

                        default:
                            throw new IllegalArgumentException("Remote WebDriver does not support browser: " + browser);
                    }
                } else {
                    switch (browser) {
                        case "firefox":
                            FirefoxOptions firefoxOptions = new FirefoxOptions();
                            if (isHeadless) {
                                firefoxOptions.addArguments("-headless");
                                firefoxOptions.addArguments("--width=1920");
                                firefoxOptions.addArguments("--height=1080");
                            }
                            driver.set(new FirefoxDriver(firefoxOptions));
                            break;

                        case "chrome":
                            ChromeOptions chromeOptions = new ChromeOptions();
                            chromeOptions.addArguments("--incognito");
                            if (isHeadless) {
                                chromeOptions.addArguments("--headless");
                                chromeOptions.addArguments("--window-size=1920,1080");
                            }
                            driver.set(new ChromeDriver(chromeOptions));
                            break;

                        case "edge":
                            EdgeOptions edgeOptions = new EdgeOptions();
                            if (isHeadless) {
                                edgeOptions.addArguments("--headless");
                                edgeOptions.addArguments("--window-size=1920,1080");
                            }
                            driver.set(new EdgeDriver(edgeOptions));
                            break;

                        default:
                            throw new IllegalArgumentException("Unsupported local browser: " + browser);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("WebDriver could not be initialized.");
            }
        }
        return driver.get();
    }


    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Driver örneğini sıfırla
        }
    }
}
