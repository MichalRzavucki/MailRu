package pages.account;

import constants.WaitTimes;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pages.PageBase;

import java.time.Duration;

public class CheckIsPersonPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(CheckIsPersonPage.class);

    private static final By IS_IT_YOU_TITLE = By.xpath("//h1[@data-test-id='header-text-recaptcha-inter']");
    private static final By THIS_IS_ME_BUTTON = By.xpath("//button[@data-test-id='recaptcha-inter-next']");
    private static final By LOGIN_BUTTON = By.xpath("//div[@class='submit-button-wrap']/button");
    private static final By VALIDATION_NOT_PASSED = By.xpath("//small[text()='Проверка не пройдена']");

    // Кнопка 'Войти'
    public By getLoginButton() {
        return LOGIN_BUTTON;
    }

    @Step("Проверка наличия блока 'Хотим убедиться, что это вы'")
    public CheckIsPersonPage checkIsItYouBlock() {
        try {
            getDriver().navigate().refresh();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitTimes.IMPLICIT_WAIT_OFF.getTime()));
            waitElementIsVisible(IS_IT_YOU_TITLE, WaitTimes.EXPLICIT_WAIT_TEN_SECONDS);
            getDriver().navigate().refresh();

            if (getDriver().findElement(IS_IT_YOU_TITLE).isDisplayed()) {
                clickThisIsMeButton();
                LOGGER.info("Блок 'Хотим убедиться, что это вы' появился");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.error("Блок 'Хотим убедиться, что это вы' не появился");
        }

        return this;
    }

    @Step("Нажатие на кнопку 'Это я'")
    private CheckIsPersonPage clickThisIsMeButton() {
        waitElementIsVisibleAndIsEnabled(THIS_IS_ME_BUTTON, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        getDriver().findElement(THIS_IS_ME_BUTTON).click();

        LOGGER.info("Нажата кнопка 'Войти другим способом'");
        return this;
    }

    @Step("Проверка наличия надписи 'Проверка не пройдена'")
    public boolean checkTextValidationNotPassed() {
        boolean isVisible = waitElementIsVisible(VALIDATION_NOT_PASSED, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);

        LOGGER.info("Появилась надпись 'Проверка не пройдена'");
        return isVisible;
    }
}
