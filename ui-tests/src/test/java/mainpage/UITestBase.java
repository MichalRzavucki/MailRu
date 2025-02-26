package mainpage;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.PageBase;

public class UITestBase {
    private static final Logger logger = LogManager.getLogger(UITestBase.class);
    private static final PageBase pageBase = new PageBase();
    private static final String URL = "https://mail.ru/";

    @BeforeAll
    public static void beforeAll() {
        logger.info("[*** Автотестирование началось ***]");
    }

    public void openUrl() {
        Allure.step("Открыть URL: " + URL);
        pageBase.openBrowser(URL);
    }

    @Step("Закрыть браузер")
    public void closeBrowser() {
        pageBase.closeBrowser();
    }

    @AfterAll
    public static void afterAll() {
        logger.info("[*** Автотестирование закончилось ***]\n");
    }
}
