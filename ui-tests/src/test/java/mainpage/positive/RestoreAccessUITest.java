package mainpage.positive;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import mainpage.UITestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import pages.MainPage;
import pages.authorization.LoginModal;
import pages.settings.RestoreAccessPage;
import pages.settings.RestorePasswordPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RestoreAccessUITest extends UITestBase {
    private static final LoginModal loginModal = new LoginModal();
    private static final RestoreAccessPage restoreAccessPage = new RestoreAccessPage();
    private static final RestorePasswordPage restorePasswordPage = new RestorePasswordPage();

    private static final String EMAIL = "gamer";
    private static final String ERROR_MESSAGE = "Страница восстановления пароля не открылась";

    @BeforeEach
    void setUp() {
        openUrl();
    }

    @Feature("Восстановление доступа")
    @DisplayName("Проверка восстановления доступа")
    @Description("Тест проверяет возможность восстановления доступа к почтовому ящику")
    @Tag("positive")
    @Tag("restoreAccessTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @RepeatedIfExceptionsTest(
            repeats = 2, minSuccess = 1,
            name = "Перепрогон {currentRepetition} из {totalRepetitions}"
    )
    void restoreAccessTest() {
        new MainPage().openLoginModal();
        loginModal
                .switchTologinModal()
                .checkAuthorizationModalOpen()
                .clickRestoreAccessButton();

        restoreAccessPage.checkRestoreAccessPageLoad();
        loginModal
                .clearField(restoreAccessPage.getMailBoxNameField())
                .typeCredential(restoreAccessPage.getMailBoxNameField(), EMAIL);

        restoreAccessPage.clickContinueButton(restoreAccessPage.getContinueButton());
        restorePasswordPage.checkRestorePasswordPageLoad();

        assertTrue(restorePasswordPage.checkUserEmail(EMAIL), ERROR_MESSAGE);
    }

    @AfterEach
    void tearDown() {
        closeBrowser();
    }
}
