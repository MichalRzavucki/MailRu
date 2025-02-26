package testpolygon.negative_tests;

import enums.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testpolygon.APITestBase;

import static enums.StatusCodes.ERROR_403;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.AuthorizationService.getTokenWithStatus;

class UnsuccessfulAuthorizationTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(UnsuccessfulAuthorizationTest.class);

    private static final String LOGIN = "admin@pflb.ru";
    private static final String PASSWORD = "wrong_password";
    private static final String SUCCESS_MESSAGE = "Пользователь с логином '%s' и паролем '%s' не смог авторизоваться";
    private static final String ERROR_MESSAGE = "Пользователь смог авторизоваться";

    @Feature("Авторизация")
    @DisplayName("Проверка неуспешной авторизации пользователя")
    @Description("Тест проверяет неуспешную авторизацию пользователя")
    @Tag("negative")
    @Tag("UnsuccessfulAuthorizationTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void unsuccessfulAuthorization() {
        assertEquals(
                ERROR_403.getStatusCode(),
                getTokenWithStatus(URL, Endpoints.LOGIN, LOGIN, PASSWORD).getStatusCode(),
                ERROR_MESSAGE
        );

        LOGGER.info(String.format(SUCCESS_MESSAGE, LOGIN, PASSWORD));
    }
}
