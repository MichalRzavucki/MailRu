package exceptions;

/**
 * Кастомное исключение неверного имени браузера
 */
public class IncorrectBrowserNameException extends Exception {
    private String browserName;

    public IncorrectBrowserNameException(String message, String browserName) {
        super(message);
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
}
