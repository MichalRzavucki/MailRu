package pages;

import org.openqa.selenium.By;
import pages.authorization.LoginModal;

/**
 * Главная страница Mail.ru
 */
public class MainPage extends PageBase {
    private static final By LOGIN_BUTTON = By.cssSelector(".resplash-btn.resplash-btn_primary");

    // Метод нажатия на кнопку логина
    public LoginModal openLoginModal() {
        getDriver().findElement(LOGIN_BUTTON).click();
        return new LoginModal();
    }
}
