package pages.settings;

import constants.WaitTimes;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.PageBase;

import java.util.Optional;

/**
 * Страница восстановления доступа к почтовому ящику
 */
public class RestoreAccessPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(RestoreAccessPage.class);

    private static final String RESTORE_ACCESS_PAGE_TITLE = "Настройки";
    private static final String ERROR_MESSAGE = "Значение отсутствует";
    private static final By RESTORE_ACCESS_FORM_TITLE = By.xpath("//h1");
    private static final By MAIL_BOX_NAME_FIELD = By.xpath("//input[@placeholder='Имя ящика']");
    private static final By CONTINUE_BUTTON = By.xpath("//button[@data-test-id='submit-button']");

    // Поле 'Имя ящика'
    public By getMailBoxNameField() {
        return MAIL_BOX_NAME_FIELD;
    }

    // Кнопка 'Продолжить'
    public By getContinueButton() {
        return CONTINUE_BUTTON;
    }

    @Step("Проверка загрузки страницы восстановления доступа к почтовому ящику")
    public RestoreAccessPage checkRestoreAccessPageLoad() {
        Object[] windowHandles = getDriver().getWindowHandles().toArray(new String[1]);
        getDriver().switchTo().window((String) windowHandles[1]);

        waitElementIsVisible(RESTORE_ACCESS_FORM_TITLE, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);

        LOGGER.info("Открыта страница восстановления доступа к почтовому ящику");
        return this;
    }

    @Step("Нажатие на кнопку {button}")
    public RestoreAccessPage clickContinueButton(By button) {
        waitElementIsVisibleAndIsEnabled(button, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        getDriver().findElement(button).click();

        LOGGER.info("Нажата кнопка 'Продолжить'");
        return this;
    }

    @Step("Получение названия страницы восстановления доступа к почтовому ящику")
    public boolean getRestoreAccessPageTitle() {
        LOGGER.info("Получение названия страницы восстановления доступа к почтовому ящику");
        var restoreAccessPageTitle = getDriver().getTitle();
        Optional<String> titleToCheck = Optional.ofNullable(restoreAccessPageTitle);

        return titleToCheck
                .orElse(ERROR_MESSAGE)
                .contains(RESTORE_ACCESS_PAGE_TITLE);
    }
}
