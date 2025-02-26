package enums;

/**
 * Перечисление браузеров для запуска
 */
public enum Browsers {
    CHROME("CHROME"),
    EDGE("EDGE");

    private final String browser;

    Browsers(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }
}
