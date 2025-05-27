package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigReader;

import java.time.Duration;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(Driver.class);


    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {

            String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigReader.get("browser").toLowerCase();
            logger.info("Testing in browser: {}", browser);
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));
            //Bu yukarıdaki satır şu komut için uygundur: mvn test -Dbrowser=chrome -Dheadless=true
            switch (browser) {
                case "edge":
                    driver.set(new EdgeDriver());
                    break;
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
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }


            driver.get().manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicit_wait")))
            );
            driver.get().manage().window().maximize();
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
