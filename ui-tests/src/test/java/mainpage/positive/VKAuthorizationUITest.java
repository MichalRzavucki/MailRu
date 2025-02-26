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
import pages.authorization.VKAuthPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Форма авторизации")
class VKAuthorizationUITest extends UITestBase {
    private static final LoginModal loginModal = new LoginModal();
    private static final VKAuthPage vkAuthPage = new VKAuthPage();

    private static final By PHONE_FIELD = By.xpath("//input[@name='phone']");
    private static final String PHONE_NUMBER = "+375111111111";
    private static final String CALL_RESET_TITLE_TEXT = "Вам поступит звонок-сброс";

    @BeforeEach
    void setUp() {
        openUrl();
    }

    @Feature("Авторизация через VK")
    @DisplayName("Авторизация через VK")
    @Description("Тест проверяет авторизацию через VK")
    @Tag("positive")
    @Tag("LoginPasswordVKTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @RepeatedIfExceptionsTest(
            repeats = 2, minSuccess = 1,
            name = "Перепрогон {currentRepetition} из {totalRepetitions}"
    )
    void positiveLoginTest() {
        new MainPage().openLoginModal();
        loginModal
                .switchTologinModal()
                .checkAuthorizationModalOpen()
                .clickLoginVKButton();

        vkAuthPage.checkVKAuthPageLoad();

        loginModal
                .clearField(PHONE_FIELD)
                .typeCredential(PHONE_FIELD, PHONE_NUMBER);

        vkAuthPage
                .clickContinueButton()
                .checkReceiveCallResetVisible();

        assertEquals(CALL_RESET_TITLE_TEXT, vkAuthPage.getCallResetTitle().getText());
    }

    @AfterEach
    void tearDown() {
        closeBrowser();
    }
}
