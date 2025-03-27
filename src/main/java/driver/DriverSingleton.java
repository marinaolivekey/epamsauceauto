package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            try {
                // Attempt to clear cache, but don’t fail if it throws an exception
                try {
                    WebDriverManager.chromedriver().clearDriverCache();
                    logger.info("Driver cache cleared successfully");
                } catch (Exception e) {
                    logger.warn("Failed to clear driver cache, proceeding anyway: {}", e.getMessage());
                }

                switch (browser) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverThreadLocal.set(new FirefoxDriver());
                        break;
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--disable-autofill", "--password-store=basic", "--no-default-browser-check");
                        driverThreadLocal.set(new ChromeDriver(options));
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driverThreadLocal.set(new EdgeDriver());
                        break;
                    default:
                        logger.error("Unsupported browser: {}", browser);
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
                logger.info("Initialized WebDriver for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                logger.error("Failed to initialize WebDriver: {}", e.getMessage());
                throw e;
            }
        }
        return driverThreadLocal.get();
    }

    public static void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            logger.info("Closed WebDriver for thread: {}", Thread.currentThread().getName());
        }
    }
}