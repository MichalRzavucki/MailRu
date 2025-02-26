package testpolygon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(APITestBase.class);
    protected static final String URL = "http://82.142.167.37:4980";

    @BeforeEach
    public void setUp() {
        LOGGER.info("\n[*** Автотестирование API началось ***]\n");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("");
        LOGGER.info("\n[*** Автотестирование API закончилось ***]");
    }
}
