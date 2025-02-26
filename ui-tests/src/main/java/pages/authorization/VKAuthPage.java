package pages.authorization;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import constants.WaitTimes;
import pages.PageBase;

/**
 * Страница авторизаци ВК
 */
public class VKAuthPage extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(VKAuthPage.class);

    private static final By VK_AUTH_PAGE = By.xpath("//div[@id='root']");
    private static final By CONTINUE_BUTTON = By.xpath("//span[text()='Продолжить']");
    private static final By CALL_RESET_TITLE = By.xpath("//span[@data-test-id='otp-title']");

    private static final int TIMEOUT = 10;
    private static final int POLLING_INTERVAL = 1;

    // Кнопка 'Продолжить'
    public WebElement continueButton() {
        return getDriver().findElement(CONTINUE_BUTTON);
    }

    // Заголовок 'Вам поступит звонок-сброс'
    public WebElement receiveCallReset() {
        return getDriver().findElement(CALL_RESET_TITLE);
    }

    @Step("Проверка загрузки вкладки авторизации через ВК")
    public VKAuthPage checkVKAuthPageLoad() {
        waitElementIsVisible(VK_AUTH_PAGE, WaitTimes.EXPLICIT_WAIT_TEN_SECONDS);

        Object[] windowHandles = getDriver().getWindowHandles().toArray(new String[1]);
        getDriver().switchTo().window((String) windowHandles[1]);
        fluentWait(VK_AUTH_PAGE, TIMEOUT, POLLING_INTERVAL);

        LOGGER.info("Открыта страница авторизации ВК");
        return this;
    }

    @Step("Нажатие на кнопку 'Продолжить'")
    public VKAuthPage clickContinueButton() {
        waitElementIsVisible(CONTINUE_BUTTON, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        continueButton().click();
        return this;
    }

    @Step("Проверка отображения заголовка 'Вам поступит звонок-сброс'")
    public VKAuthPage checkReceiveCallResetVisible() {
        waitElementIsVisible(CALL_RESET_TITLE, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        return this;
    }

    @Step("Получение заголовка звонка-сброса")
    public WebElement getCallResetTitle() {
        return getDriver().findElement(CALL_RESET_TITLE);
    }
}
