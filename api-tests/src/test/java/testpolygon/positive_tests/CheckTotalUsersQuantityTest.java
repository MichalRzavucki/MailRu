package testpolygon.positive_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testpolygon.APITestBase;
import service.UserService;
import specifications.Specifications;

import static enums.StatusCodes.SUCCESS_200;

@Epic("Api tests")
class CheckTotalUsersQuantityTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(CheckFirstUserUserServiceTest.class);

    private static final String SUCCESS_MESSAGE = "Общее количество пользователей в базе данных составляет '%d'";
    private static final String ERROR_MESSAGE = "Общее количество пользователей в базе данных не равно ожидаемому результату";

    @Feature("Пользователи")
    @DisplayName("Проверка количества пользователей в базе данных")
    @Description("Тест проверяет количество пользователей в базе данных")
    @Tag("positive")
    @Tag("checkUsersQuantityTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void checkTotalUsersQuantity() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL), Specifications.responseSpecStatusCode(SUCCESS_200)
        );

        long actualUsersQuantity = UserService.getAllUsers().size();
        Assertions.assertEquals(actualUsersQuantity, UserService.getAllUsers().size(), ERROR_MESSAGE);

        LOGGER.info(String.format(SUCCESS_MESSAGE, actualUsersQuantity));
    }
}
