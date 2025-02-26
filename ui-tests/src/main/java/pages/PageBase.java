package pages;

import enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.WaitTimes;
import util.BrowserChanger;

import java.time.Duration;

/**
 * Базовый класс для всех страниц
 */
public class PageBase {
    private static final Logger LOGGER = LogManager.getLogger(PageBase.class);

    // Метод открытия браузера
    public void openBrowser(String url) {
        BrowserChanger.runDriver(Browsers.CHROME).get(url);
        LOGGER.info("Браузер 'CHROME' открыт");
    }

    // Метод закрытия браузера
    public void closeBrowser() {
        BrowserChanger.getDriver().quit();
        BrowserChanger.setDriver(null);
        LOGGER.info("Браузер 'CHROME' закрыт");
    }

    // Кастомный метод ожидания видимости элемента
    public boolean waitElementIsVisible(By element, WaitTimes waitTimes) {
        boolean isElementVisible = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTimes.getTime()))
                .until(e -> e.findElement(element).isDisplayed());

        if (!isElementVisible) {
            LOGGER.error("Элемент {} не найден на странице", element);
            return false;
        }

        return true;
    }

    // Кастомный метод ожидания доступности элемента
    public boolean waitElementIsEnabled(By element, WaitTimes waitTimes) {
        boolean isElementEnabled = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTimes.getTime()))
                .until(e -> e.findElement(element).isEnabled());

        if (!isElementEnabled) {
            LOGGER.error("Элемент {} не доступен для взаимодействия", element);
            return false;
        }

        return true;
    }

    // Одновременное ожидание видимости и доступности элемента
    public void waitElementIsVisibleAndIsEnabled(By element, WaitTimes waitTimes) {
        if (!waitElementIsVisible(element, waitTimes) && !waitElementIsEnabled(element, waitTimes)) {
            LOGGER.error("Элемент {} не найден на странице и недоступен для взаимодействия", element);
        }
    }

    // Ожидание элемента с интервалом
    public void fluentWait(By element, int timeOut, int pollingInterval) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingInterval))
                .ignoring(StaleElementReferenceException.class);

        if (Boolean.TRUE.equals(wait.until(e -> e.findElement(element).isDisplayed()))) {
            LOGGER.error("Элемент {} не найден на странице по истечению таймаута {} ", element, timeOut);
        }
    }

    // Остановка Селениума на определённый период времени
    public PageBase seleniumSleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            LOGGER.error("Ошибка прерывания нити");
            Thread.currentThread().interrupt();
        }

        return this;
    }

    // Получение экземпляра вебдрайвера
    public WebDriver getDriver() {
        return BrowserChanger.getDriver();
    }
}
