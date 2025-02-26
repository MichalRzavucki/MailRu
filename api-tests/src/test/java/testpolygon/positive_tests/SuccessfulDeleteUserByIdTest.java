package testpolygon.positive_tests;

import enums.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.AuthorizationResponse;
import testpolygon.APITestBase;
import service.AuthorizationService;
import service.UserService;
import specifications.Specifications;

import static enums.Endpoints.USER;
import static enums.StatusCodes.SUCCESS_204;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SuccessfulDeleteUserByIdTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(SuccessfulDeleteUserByIdTest.class);

    private static final String LOGIN = "admin@pflb.ru";
    private static final String PASSWORD = "admin";
    private static final int USER_TO_DELETE = 2169;
    private static final String SUCCESS_MESSAGE = "Пользователь с ID %d успешно удалён";
    private static final String ERROR_MESSAGE = "Пользователь с ID %d не найден";

    @Feature("Пользователи")
    @DisplayName("Удаление пользователя по ID")
    @Description("Тест проверяет удаление пользователя по ID")
    @Tag("positive")
    @Tag("SuccessfulDeleteUserByIdTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void deleteUserById() {
        AuthorizationResponse authResponse = AuthorizationService.getToken(URL, Endpoints.LOGIN, LOGIN, PASSWORD);
        Specifications.installSpecification(
                Specifications.requestSpec(URL), Specifications.responseSpecStatusCode(SUCCESS_204)
        );

        Response response = UserService.deleteUserById(URL, USER, authResponse.getAccess_token(), USER_TO_DELETE);

        assertEquals(
                SUCCESS_204.getStatusCode(),
                response.getStatusCode(),
                String.format(ERROR_MESSAGE, USER_TO_DELETE)
        );
        LOGGER.info(String.format(SUCCESS_MESSAGE, USER_TO_DELETE));
    }
}
