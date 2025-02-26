package constants;

/**
 * Перечисление со временем для ожиданий
 */
public enum WaitTimes {
    IMPLICIT_WAIT_OFF(0),
    EXPLICIT_WAIT_FIVE_SECONDS(5),
    EXPLICIT_WAIT_TEN_SECONDS(10),
    EXPLICIT_WAIT_FIFTEEN_SECONDS(15),
    EXPLICIT_WAIT_TWENTY_SECONDS(20),
    EXPLICIT_WAIT_THIRTY_SECONDS(30);

    private final int time;

    WaitTimes(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}