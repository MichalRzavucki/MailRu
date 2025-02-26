package pages.authorization;

import constants.WaitTimes;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import pages.PageBase;

import java.time.Duration;

/**
 * Модальное окно логина
 */
public class LoginModal extends PageBase {
    private static final Logger LOGGER = LogManager.getLogger(LoginModal.class);

    private static final By LOGIN_MODAL_IFRAME = By.xpath("//iframe[@class='ag-popup__frame__layout__iframe']");
    private static final By LOGIN_MODAL_TITLE_FIELD = By.xpath("//h1[@data-test-id]");
    private static final By LOGIN_TO_MAIL_WITH_VK_ID_TITLE = By.xpath("//h1[@data-test-id='text-enter-mail-with-vkid']");
    private static final By LOGIN_BUTTON = By.xpath("//div[@class='submit-button-wrap']/button");
    private static final By PASSWORD_BUTTON = By.xpath("//button[@data-test-id='submit-button']");
    private static final By LOGIN_ERROR_TEXT = By.xpath("//div[@data-test-id='password-input-error']");
    private static final By ACCOUNT_NOT_REGISTERED_TEXT = By.xpath("//small[@data-test-id='accountNotRegistered']");
    private static final By LOGIN_WITH_OTHER_VARIANT = By.xpath("//button[@data-test-id='bind-screen-vkid-change-restore-type-btn']");
    private static final By ADD_GMAIL_POPUP = By.xpath("//div[text()='Теперь можно добавить почту Gmail']");
    private static final By LOGIN_VK_BUTTON = By.xpath("//button[@data-test-id='social-vk']");
    private static final By RESTORE_ACCESS_BUTTON = By.xpath("//a[@data-test-id='remind']");
    private static final By WRITE_EMAIL_BUTTON = By.xpath("//span[@class='compose-button__wrapper']");

    private static final String LOGIN_TITLE = "Войти в аккаунт";
    private static final String PASSWORD_TITLE = "Введите пароль";
    private static final String REASON = "Поле не загрузилось";

    // Кнопка 'Логин'
    public By getLoginButton() {
        return LOGIN_BUTTON;
    }

    // Кнопка 'Пароль'
    public By getPasswordButton() {
        return PASSWORD_BUTTON;
    }

    // Кнопка 'Написать письмо'
    public By getWriteEmailButton() {
        return WRITE_EMAIL_BUTTON;
    }

    @Step("Переход в модальное окно авторизации")
    public LoginModal switchTologinModal() {
        waitElementIsVisible(LOGIN_MODAL_IFRAME, WaitTimes.EXPLICIT_WAIT_THIRTY_SECONDS);

        WebElement iframe = getDriver().findElement(LOGIN_MODAL_IFRAME);
        getDriver().switchTo().frame(iframe);

        LOGGER.info("Выполнен переход в модальное окно авторизации");
        return this;
    }

    // Метод получения названия страницы
    private WebElement getModalTitle() {
        LOGGER.info("Получено название страницы");
        return getDriver().findElement(LOGIN_MODAL_TITLE_FIELD);
    }

    @Step("Проверка открытия модального окна авторизации")
    public LoginModal checkAuthorizationModalOpen() {
        waitElementIsVisible(LOGIN_MODAL_TITLE_FIELD, WaitTimes.EXPLICIT_WAIT_THIRTY_SECONDS);

        if (getModalTitle().getText().equals(LOGIN_TITLE)) {
            LOGGER.info("Модальное окно авторизации открылось успешно");
            return this;
        }

        if (getModalTitle().getText().equals(PASSWORD_TITLE)) {
            LOGGER.info("Модальное окно авторизации открылось успешно");
            return this;
        }

        LOGGER.info("Модальное окно Авторизации не появилось");
        throw new NoSuchElementException(REASON);
    }

    @Step("Проверка наличия уведомления 'Теперь можно добавить почту Gmail'")
    public LoginModal checkGmailPopup() {
        try {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitTimes.IMPLICIT_WAIT_OFF.getTime()));
            WebElement gmailPopup = getDriver().findElement(ADD_GMAIL_POPUP);

            if (gmailPopup.isDisplayed()) {
                gmailPopup.click();
                LOGGER.info("Уведомление 'Теперь можно добавить почту Gmail' успешно обработано");
            }

        } catch (NoSuchElementException | TimeoutException | ElementClickInterceptedException e) {
            return this;
        }

        return this;
    }

    // Метод очистки поля
    public LoginModal clearField(By field) {
        Allure.step("Очистка поля", () -> {
            waitElementIsVisibleAndIsEnabled(field, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
            getDriver().findElement(field).sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        });

        LOGGER.info("Поле очищено");
        return this;
    }

    // Метод ввода крэдов
    public LoginModal typeCredential(By field, String data) {
        Allure.step(String.format("Ввести '%s'", data), () -> {
            waitElementIsVisibleAndIsEnabled(field, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
            getDriver().findElement(field).sendKeys(data);
        });

        LOGGER.info("Введены данные");
        return this;
    }

    @Step("Нажатие на кнопку логина")
    public LoginModal clickLoginButton(By button) {
        waitElementIsVisibleAndIsEnabled(button, WaitTimes.EXPLICIT_WAIT_FIFTEEN_SECONDS);
        getDriver().findElement(button).click();

        LOGGER.info("Кнопка логина нажата");
        return this;
    }

    @Step("Проверка модального окна 'Входите в Mail с VK ID'")
    public LoginModal checkLoginWithVKIDModal() {
        try {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitTimes.IMPLICIT_WAIT_OFF.getTime()));

            new PageBase().seleniumSleep(5000);
            if (getDriver().findElement(LOGIN_TO_MAIL_WITH_VK_ID_TITLE).isDisplayed()) {
                clickLoginWithAnotherVariantButton();
                LOGGER.info("Модальное окно 'Входите в Mail с VK ID' обработано");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.error("Модальное окно 'Входите в Mail с VK ID' не обработано");
            throw new NoSuchElementException("Модальное окно " + LOGIN_TO_MAIL_WITH_VK_ID_TITLE + "не найдено");
        }

        return this;
    }

    @Step("Нажатие на кнопку 'Войти другим способом'")
    private LoginModal clickLoginWithAnotherVariantButton() {
        waitElementIsVisibleAndIsEnabled(LOGIN_WITH_OTHER_VARIANT, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        getDriver().findElement(LOGIN_WITH_OTHER_VARIANT).click();

        LOGGER.info("Нажата кнопка 'Войти другим способом'");
        return this;
    }

    @Step("Нажатие на кнопку 'Войти через VK'")
    public LoginModal clickLoginVKButton() {
        waitElementIsVisibleAndIsEnabled(LOGIN_VK_BUTTON, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        getDriver().findElement(LOGIN_VK_BUTTON).click();

        LOGGER.info("Нажата кнопка 'Войти через VK'");
        return this;
    }

    @Step("Нажатие на кнопку 'Восстановить доступ'")
    public LoginModal clickRestoreAccessButton() {
        waitElementIsVisibleAndIsEnabled(RESTORE_ACCESS_BUTTON, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        getDriver().findElement(RESTORE_ACCESS_BUTTON).click();

        LOGGER.info("Нажата кнопка 'Восстановить доступ'");
        return this;
    }

    @Step("Проверка наличия ошибки ввода пароля 'Неверный пароль, попробуйте ещё раз'")
    public String getLoginErrorText() {
        waitElementIsVisibleAndIsEnabled(LOGIN_ERROR_TEXT, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        return getDriver().findElement(LOGIN_ERROR_TEXT).getText();
    }

    @Step("Проверка наличия ошибки ввода пароля 'Такой аккаунт не зарегистрирован'")
    public String getSuchAccountNotRegisteredText() {
        waitElementIsVisibleAndIsEnabled(ACCOUNT_NOT_REGISTERED_TEXT, WaitTimes.EXPLICIT_WAIT_FIVE_SECONDS);
        fluentWait(ACCOUNT_NOT_REGISTERED_TEXT, 60_000, 1_000);
        return getDriver().findElement(ACCOUNT_NOT_REGISTERED_TEXT).getText();
    }

    @Step("Проверка отображения кнопки 'Написать письмо'")
    public boolean checkWriteEmailButtonPresent() {
        return getDriver().findElement(WRITE_EMAIL_BUTTON).isDisplayed();
    }
}
