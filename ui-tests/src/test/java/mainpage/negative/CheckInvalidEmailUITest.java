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
import pages.authorization.LoginModal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Форма авторизации")
class CheckInvalidEmailUITest extends UITestBase {
    private static final LoginModal loginModal = new LoginModal();

    private static final By USER_NAME_FIELD = By.xpath("//input[@name='username']");
    private static final String USERNAME = "d83j93rc@mail.ru";
    private static final String INCORRECT_ACCOUNT_TEXT = "Такой аккаунт не зарегистрирован";

    @BeforeEach
    void setUp() {
        openUrl();
    }

    @Feature("Авторизация через логин и пароль")
    @DisplayName("Проверка ввода неправильного Email")
    @Description("Тест проверяет ввод неправильного Email")
    @Tag("positive")
    @Tag("incorrectEmailTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @RepeatedIfExceptionsTest(
            repeats = 2, minSuccess = 1,
            name = "Перепрогон {currentRepetition} из {totalRepetitions}"
    )
    void typeIncorrectEmailTestTest() {
        new MainPage().openLoginModal();
        loginModal
                .switchTologinModal()
                .checkAuthorizationModalOpen()
                .checkGmailPopup()
                .typeCredential(USER_NAME_FIELD, USERNAME)
                .clickLoginButton(loginModal.getLoginButton());

        assertEquals(INCORRECT_ACCOUNT_TEXT, loginModal.getSuchAccountNotRegisteredText());
    }

    @AfterEach
    void tearDown() {
        closeBrowser();
    }
}
