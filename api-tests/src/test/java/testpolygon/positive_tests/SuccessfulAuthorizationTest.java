package testpolygon.positive_tests;

import enums.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testpolygon.APITestBase;
import service.AuthorizationService;
import specifications.Specifications;

import static enums.StatusCodes.SUCCESS_202;

class SuccessfulAuthorizationTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(SuccessfulAuthorizationTest.class);

    private static final String LOGIN = "admin@pflb.ru";
    private static final String PASSWORD = "admin";
    private static final String SUCCESS_MESSAGE = "Пользователь с логином '%s' и паролем '%s' авторизовался";

    @Feature("Авторизация")
    @DisplayName("Проверка успешной авторизации пользователя")
    @Description("Тест проверяет успешную авторизацию пользователя")
    @Tag("positive")
    @Tag("successfulAuthorizationTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void successfulAuthorization() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL), Specifications.responseSpecStatusCode(SUCCESS_202)
        );

        Assertions.assertFalse(
                AuthorizationService.getToken(URL, Endpoints.LOGIN, LOGIN, PASSWORD).getAccess_token().isEmpty()
        );

        LOGGER.info(String.format(SUCCESS_MESSAGE, LOGIN, PASSWORD));
    }
}
