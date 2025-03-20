package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPage {

    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);
    protected WebDriverWait wait;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected abstract AbstractPage openPage();

    public AbstractPage(WebDriver driver) {
        if (driver == null) {
            logger.error("WebDriver instance cannot be null");
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
        logger.info("Initialized AbstractPage with driver and PageFactory");
    }

}
