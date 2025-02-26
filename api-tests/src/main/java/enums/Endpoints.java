package enums;

/**
 * Перечисление эндпоинтов для API
 */
public enum Endpoints {
    LOGIN("/login"),
    USER("/user");

    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
