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
import pojo.AuthorizationResponse;
import pojo.CreateUserRequest;
import pojo.CreateUserResponse;
import service.AuthorizationService;
import service.UserService;
import testpolygon.APITestBase;

import static enums.Endpoints.USER;

class CreateUserTest extends APITestBase {
    private static final Logger LOGGER = LogManager.getLogger(CheckFirstUserUserServiceTest.class);

    private static final String LOGIN = "admin@pflb.ru";
    private static final String PASSWORD = "admin";

    private static final String FIRST_NAME = "Михал";
    private static final String SECOND_NAME = "Михалыч";
    private static final int AGE = 40;
    private static final String SEX = "MALE";
    private static final double MONEY = 10.00;
    private static final String SUCCESS_MESSAGE = "В базе данных создан пользователь %s";

    @Feature("Авторизация")
    @DisplayName("Проверка создания нового пользователя в базе данных")
    @Description("Тест проверяет создание нового пользователя в базе данных")
    @Tag("positive")
    @Tag("createUserTest")
    @Owner("Ржеуцкий Михаил Михайлович")
    @Test
    void createUser() {
        AuthorizationResponse authResponse = AuthorizationService.getToken(URL, Endpoints.LOGIN, LOGIN, PASSWORD);

        CreateUserRequest newUser = new CreateUserRequest(FIRST_NAME, SECOND_NAME, AGE, SEX, MONEY);
        CreateUserResponse createUserResponse =
                UserService.createUser(URL, USER, newUser, authResponse.getAccess_token());

        Assertions.assertAll(
                "Проверка создания пользователя в базе данных",
                () -> Assertions.assertEquals(FIRST_NAME, createUserResponse.getFirstName(), "Имя не указано"),
                () -> Assertions.assertEquals(SECOND_NAME, createUserResponse.getSecondName(), "Фамилия не указана"),
                () -> Assertions.assertEquals(AGE, createUserResponse.getAge(), "Возраст не указан"),
                () -> Assertions.assertEquals(SEX, createUserResponse.getSex(), "Пол не указан"),
                () -> Assertions.assertEquals(MONEY, createUserResponse.getMoney(), "Сумма денег не указана")
        );

        LOGGER.info(String.format(SUCCESS_MESSAGE, createUserResponse));
    }
}
