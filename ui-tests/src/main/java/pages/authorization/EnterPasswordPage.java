package pages.authorization;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import constants.WaitTimes;
import pages.PageBase;

import java.time.Duration;

public class EnterPasswordPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(EnterPasswordPage.class);

    private static final By ENTER_PASSWORD_FIELD = By.xpath("//input[@placeholder='Введите пароль']");
    private static final By SAVE_TYPING_TEXT = By.xpath("//span[text()='Сохранить вход']");
    private static final By LOGIN_BUTTON = By.xpath("//div[starts-with(@class, 'vkuiFormItem')]/button");

    // Поле 'Пароль'
    public By getPasswordField() {
        return ENTER_PASSWORD_FIELD;
    }

    // Текст 'Сохранить вход' рядом с галочкой
    public By getSaveTypingText() {
        return SAVE_TYPING_TEXT;
    }

    // Кнопка 'Написать письмо'
    public By getLoginButton() {
        return LOGIN_BUTTON;
    }

    @Step("Проверка открытия страницы 'Введите пароль'")
    public EnterPasswordPage checkTypePasswordPageOpened() {
        try {
            if (getDriver().findElement(getSaveTypingText()).isDisplayed()) {
                getDriver().navigate().refresh();
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitTimes.IMPLICIT_WAIT_OFF.getTime()));
                waitElementIsVisible(getSaveTypingText(), WaitTimes.EXPLICIT_WAIT_TEN_SECONDS);
                getDriver().navigate().refresh();

                LOGGER.info("Блок 'Хотим убедиться, что это вы' появился");
            }
            return this;
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.error("Страница 'Введите пароль' не появилась");
        }
        return this;
    }

    // Ввод пароля
    public EnterPasswordPage typePassword(String data) {
        Allure.step(String.format("Ввести '%s'", data), () -> {
            waitElementIsVisibleAndIsEnabled(getPasswordField(), WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
            getDriver().findElement(getPasswordField()).sendKeys(data);
        });

        LOGGER.info(String.format("Введён пароль '%s', data"));
        return this;
    }

    @Step("Нажатие на кнопку логина")
    public EnterPasswordPage clickLoginButton() {
        waitElementIsVisibleAndIsEnabled(getLoginButton(), WaitTimes.EXPLICIT_WAIT_FIFTEEN_SECONDS);
        getDriver().findElement(getLoginButton()).click();

        LOGGER.info("Кнопка логина нажата");
        return this;
    }
}
