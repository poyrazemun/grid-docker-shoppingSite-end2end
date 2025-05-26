package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

import java.time.Duration;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {

            String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigReader.get("browser").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            //Bu yukarıdaki satır şu komut için uygundur: mvn test -Dbrowser=chrome -Dheadless=true
            switch (browser) {
                case "edge":
                    driver.set(new EdgeDriver());
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless");
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
