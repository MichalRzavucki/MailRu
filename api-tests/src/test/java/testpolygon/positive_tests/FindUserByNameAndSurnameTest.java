package testpolygon.positive_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testpolygon.APITestBase;
import service.UserService;
import specifications.Specifications;

import static enums.StatusCodes.SUCCESS_200;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUserByNameAndSurnameTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(FindUserByNameAndSurnameTest.class);

    private static final String USER_FIRST_NAME = "Richer";
    private static final String USER_SECOND_NAME = "Don";
    private static final String EXPECTED_USER_NAME = "Richer Don";
    private static final String SUCCESS_MESSAGE = "В базе данных найден пользователь: '%s'";
    private static final String ERROR_MESSAGE = "Пользователь с указанным именем и фамилией отсутствует в базе данных";

    @Feature("Пользователи")
    @DisplayName("Поиск пользователя по имени и фамилии")
    @Description("Тест проверяет поиск пользователя по имени и фамилии")
    @Tag("positive")
    @Tag("checkFirstUserFieldsQuantityTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void checkFirstUserFieldsQuantity() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL), Specifications.responseSpecStatusCode(SUCCESS_200)
        );

        String foundUserNameAndSurname = UserService.findUserByNameAndSurname(UserService.getAllUsers(), USER_FIRST_NAME, USER_SECOND_NAME);

        assertEquals(EXPECTED_USER_NAME, foundUserNameAndSurname, ERROR_MESSAGE);
        LOGGER.info(String.format(SUCCESS_MESSAGE, foundUserNameAndSurname));
    }
}
