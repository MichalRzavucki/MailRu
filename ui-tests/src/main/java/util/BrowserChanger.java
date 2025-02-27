package util;

import enums.Browsers;
import exceptions.IncorrectBrowserNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static enums.Browsers.CHROME;

public class BrowserChanger {
    private static final Logger LOGGER = LogManager.getLogger(BrowserChanger.class);

    private static WebDriver driver;
    private static final String ROOT_PATH = "src/main/resources/webdrivers/";
    private static final String CHROME_BROWSER = ROOT_PATH + "chromedriver-132.0.6834.83.exe";
    private static final String EDGE_BROWSER = ROOT_PATH + "msedgedriver-131.0.2903.51.exe";

    private BrowserChanger() {
    }

    // Инициализация необходимого вебдрайвера
    private static WebDriver initializeDriver(Browsers browser) {
        WebDriver driver = null;

        if (browser.getBrowser().equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", CHROME_BROWSER);
            driver = new ChromeDriver();
        } else if (browser.getBrowser().equals("EDGE")) {
            System.setProperty("webdriver.edge.driver", EDGE_BROWSER);
            driver = new EdgeDriver();
        } else {
            try {
                throw new IncorrectBrowserNameException("Неправильное имя браузера", CHROME.getBrowser());
            } catch (IncorrectBrowserNameException e) {
                LOGGER.error("Указано неправильное имя браузера в инициализаторе");
            }
        }

        driver.manage().window().maximize();

        return driver;
    }

    // Получение единственного экземпляра вебдрайвера
    public static WebDriver runDriver(Browsers browser) {
        if (driver == null) {
            driver = initializeDriver(browser);
        }

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driverState) {
        driver = driverState;
    }
}
