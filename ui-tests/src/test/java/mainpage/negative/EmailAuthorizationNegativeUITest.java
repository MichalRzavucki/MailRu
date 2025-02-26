package mainpage.negative;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import mainpage.UITestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.account.CheckIsPersonPage;
import pages.authorization.EnterPasswordPage;
import pages.authorization.LoginModal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Форма авторизации")
class EmailAuthorizationNegativeUITest extends UITestBase {
    private static final LoginModal loginModal = new LoginModal();
    private static final CheckIsPersonPage checkIsPersonPage = new CheckIsPersonPage();

    private static final By USER_NAME_FIELD = By.xpath("//input[@name='username']");
    private static final By PASSWORD_NAME_FIELD = By.xpath("//input[@name='password']");
    private static final String USERNAME_ONE = "abc@mail.ru";
    private static final String USERNAME_TWO = "abcde@mail.ru";
    private static final String PASSWORD = "d83dad9f";
    private static final String INCORRECT_PASSWORD_TEXT = "Неверный пароль, попробуйте ещё раз";
    private static final String VALIDATION_NOT_PASSED_TEXT = "Проверка не пройдена";

    @BeforeEach
    void setUp() {
        openUrl();
    }

    @Feature("Авторизация через логин и пароль")
    @DisplayName("Авторизация неправильным логином и паролем")
    @Description("Тест проверяет авторизацию по неправильному логину и паролю")
    @Tag("positive")
    @Tag("EmailAuthInvalidCredTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @RepeatedIfExceptionsTest(
            repeats = 2, minSuccess = 1,
            name = "Перепрогон {currentRepetition} из {totalRepetitions}"
    )
    void negativeLoginTest() {
        new MainPage().openLoginModal();

        loginModal
                .switchTologinModal()
                .checkAuthorizationModalOpen()
                .checkGmailPopup()
                .typeCredential(USER_NAME_FIELD, USERNAME_ONE)
                .clickLoginButton(loginModal.getLoginButton())
                .checkLoginWithVKIDModal();
        new EnterPasswordPage()
                .checkTypePasswordPageOpened() // TODO Метод перезагружает страницу когда в неё не зашло. Поправить
                .typePassword(PASSWORD)
                .clickLoginButton();
        loginModal
                .checkAuthorizationModalOpen()
                .typeCredential(PASSWORD_NAME_FIELD, PASSWORD)
                .clickLoginButton(loginModal.getLoginButton());

        new CheckIsPersonPage().checkIsItYouBlock();

        loginModal
                .typeCredential(PASSWORD_NAME_FIELD, PASSWORD)
                .clickLoginButton(checkIsPersonPage.getLoginButton());

        assertTrue(
                checkIsPersonPage.checkTextValidationNotPassed(),
                "Надпись " + VALIDATION_NOT_PASSED_TEXT + " не появилась"
        );
    }

    @AfterEach
    void tearDown() {
        closeBrowser();
    }
}
