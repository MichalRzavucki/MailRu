package enums;

/**
 * Перечисление со статус-кодами для API
 */
public enum StatusCodes {
    SUCCESS_200(200),
    SUCCESS_202(202),
    SUCCESS_204(204),
    ERROR_403(403),
    ERROR_404(404);

    private final int statusCode;

    StatusCodes(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
