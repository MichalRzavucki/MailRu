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

class CheckFirstUserUserServiceTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(CheckFirstUserUserServiceTest.class);

    private static final int EXPECTED_FIELDS_QUANTITY = 6;
    private static final String SUCCESS_MESSAGE = "Количество полей в объекте пользователя равно %d";
    private static final String ERROR_MESSAGE = "Количество полей в объекте пользователя не равно 6";

    @Feature("Пользователи")
    @DisplayName("Проверка количества полей в объекте у первого пользователя")
    @Description("Тест проверяет количество полей в объекте у первого пользователя")
    @Tag("positive")
    @Tag("checkFirstUserFieldsQuantityTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void checkFirstUserFieldsQuantity() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL), Specifications.responseSpecStatusCode(SUCCESS_200)
        );

        long fieldsQuantity = UserService.countUserFields(UserService.getAllUsers());
        assertEquals(EXPECTED_FIELDS_QUANTITY, fieldsQuantity, ERROR_MESSAGE);

        LOGGER.info(String.format(SUCCESS_MESSAGE, fieldsQuantity));
    }
}
