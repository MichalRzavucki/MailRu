package pages.authorization;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.PageBase;
import constants.WaitTimes;

import java.util.Optional;

/**
 * Страница почтового ящика
 */
public class MailBoxPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(MailBoxPage.class);

    private static final String MAIL_BOX_TITLE = "Почта Mail";
    private static final String ERROR_MESSAGE = "Значение отсутствует";
    private static final By FILTER = By.xpath("//span[text()='Фильтр']");

    @Step("Проверка загрузки страницы почтового ящика")
    public MailBoxPage checkMailBoxPageLoad() {
        getDriver().switchTo().defaultContent();
        waitElementIsVisible(FILTER, WaitTimes.EXPLICIT_WAIT_THIRTY_SECONDS);

        LOGGER.info("Открыта страница почтового ящика");
        return this;
    }

    @Step("Получение названия страницы почтового ящика")
    public boolean getMailBoxPageTitle() {
        LOGGER.info("Получение названия страницы почтового ящика");
        var mailBoxTitle = getDriver().getTitle();
        Optional<String> titleToCheck = Optional.ofNullable(mailBoxTitle);

        return titleToCheck
                .orElse(ERROR_MESSAGE)
                .contains(MAIL_BOX_TITLE);
    }
}
