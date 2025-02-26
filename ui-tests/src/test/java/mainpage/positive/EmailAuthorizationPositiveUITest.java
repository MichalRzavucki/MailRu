package mainpage.positive;

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
import pages.authorization.LoginModal;
import pages.authorization.MailVKIDPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Форма авторизации")
class EmailAuthorizationPositiveUITest extends UITestBase {
    private static final LoginModal loginModal = new LoginModal();

    private static final By USER_NAME_FIELD = By.xpath("//input[@name='username']");
    private static final By PASSWORD_NAME_FIELD = By.xpath("//input[@name='password']");

    private static final String USERNAME = "test_login"; // Нужно подставить свои крэды
    private static final String PASSWORD = "test_password"; // Нужно подставить свои крэды
    private static final String ERROR_MESSAGE = "Не удалось авторизоваться";

    @BeforeEach
    void setUp() {
        openUrl();
    }

    @Feature("Авторизация через логин и пароль")
    @DisplayName("Авторизация правильным логином и паролем")
    @Description("Тест проверяет авторизацию по правильному логину и паролю")
    @Tag("positive")
    @Tag("EmailAuthValidCredTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @RepeatedIfExceptionsTest(
            repeats = 2, minSuccess = 1,
            name = "Перепрогон {currentRepetition} из {totalRepetitions}"
    )
    void positiveLoginTest() {
        // TODO: Автотест будет работать если подставить реальные крэды
        new MainPage().openLoginModal();

        loginModal
                .switchTologinModal()
                .checkAuthorizationModalOpen()
                .checkGmailPopup()
                .typeCredential(USER_NAME_FIELD, USERNAME)
                .clickLoginButton(loginModal.getLoginButton())
                .checkLoginWithVKIDModal()
                .checkAuthorizationModalOpen()
                .typeCredential(PASSWORD_NAME_FIELD, PASSWORD)
                .clickLoginButton(loginModal.getPasswordButton());
        new MailVKIDPage().checkMailVKIDPageOpened();

        assertTrue(loginModal.checkWriteEmailButtonPresent(), ERROR_MESSAGE);
    }

    @AfterEach
    void tearDown() {
        closeBrowser();
    }
}
