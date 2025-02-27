package pages.authorization;

import constants.WaitTimes;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pages.PageBase;

/**
 * Страница авторизации Mail x VK ID
 */
public class MailVKIDPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(MailVKIDPage.class);

    private static final By MAIL_VK_ID_LOGO = By.xpath("//div[@class='vkc__AppScreen-module__headerIllustration']");
    // TODO Добавить локатор поля ввода пароля как страница бутет отловлена

    @Step("Проверка открытия страницы 'Mail X VK ID'")
    public MailVKIDPage checkMailVKIDPageOpened() {
        try {
            waitElementIsVisible(MAIL_VK_ID_LOGO, WaitTimes.EXPLICIT_WAIT_TEN_SECONDS);
            if (getDriver().findElement(MAIL_VK_ID_LOGO).isDisplayed()) {
                LOGGER.info("Отобразилась страница 'Mail X VK ID'");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            return this;
        }
        return this;
    }
}
