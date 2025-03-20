package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends AbstractPage {

    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);
    private final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(css = ".app_logo")
    private WebElement mainPageTitle;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MainPage openPage() {
        logger.info("Opening main page...");
        driver.navigate().to(BASE_URL);
        logger.info("Opened main page");
        return this;
    }

    public String getTitleLoggedInUser() {
        logger.info("Retrieving main page title...");
        String title = mainPageTitle.getText();
        logger.info("Retrieved main page title: '{}'", title);
        return title;
    }
}
