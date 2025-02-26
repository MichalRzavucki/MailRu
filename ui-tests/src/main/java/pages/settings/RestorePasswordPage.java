package pages.settings;

import constants.WaitTimes;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pages.PageBase;

public class RestorePasswordPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(RestorePasswordPage.class);

    private static final String RESTORE_PASSWORD_PAGE_TITLE = "Восстановление пароля";
    private static final String ERROR_MESSAGE = "Значение отсутствует";
    private static final By RESTORE_PASSWORD_FORM_TITLE = By.xpath("//span[@class='b-panel__header__text']");

    @Step("Проверка загрузки страницы восстановления пароля")
    public RestorePasswordPage checkRestorePasswordPageLoad() {
        waitElementIsVisible(RESTORE_PASSWORD_FORM_TITLE, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);

        LOGGER.info("Открыта страница восстановления пароля");
        return this;
    }

    @Step("Получение названия формы восстановления пароля")
    private String getRestorePasswordFormTitle() {
        return getDriver().findElement(RESTORE_PASSWORD_FORM_TITLE).getText();
    }

    @Step("Проверка отображения нужного email в форме изменения пароля")
    public boolean checkUserEmail(String email) {
        return getRestorePasswordFormTitle().contains(email);
    }
}
