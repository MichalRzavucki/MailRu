package service;

import enums.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.CreateUserRequest;
import pojo.CreateUserResponse;
import pojo.UsersResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Сервисный класс, отвечающий за страницу с пользователями
 */
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private static final String USERS_ENDPOINT = "/users";
    private static final String USER_FIRST_NAME_ERROR_MESSAGE = "Пользователь с именем '%s' не найден";
    private static final String USER_LAST_NAME_ERROR_MESSAGE = "Пользователь с фамилией '%s' не найден";

    // Подсчёт количества полей в объекте пользователя
    public static <T> long countUserFields(List<T> t) {
        LOGGER.info("Подсчёт количества полей в объекте пользователя");

        return Arrays.stream(Objects.requireNonNull(t.stream()
                .findFirst()
                .orElse(null)).getClass().getDeclaredFields()).count();
    }

    // Поиск пользователя по имени и фамилии
    public static String findUserByNameAndSurname(List<UsersResponse> users, String name, String surname) {
        LOGGER.info("Поиск пользователя по имени и фамилии");

        String foundName = Objects.requireNonNull(users.stream()
                .filter(firstName -> firstName.getFirstName().equals(name))
                .findFirst()
                .orElse(null), String.format(USER_FIRST_NAME_ERROR_MESSAGE, name)).getFirstName();

        String foundSurname = Objects.requireNonNull(users.stream()
                .filter(lastName -> lastName.getSecondName().equals(surname))
                .findFirst()
                .orElse(null), String.format(USER_LAST_NAME_ERROR_MESSAGE, name)).getSecondName();

        return String.format("%s %s", foundName, foundSurname);
    }

    // Создание пользователя
    public static CreateUserResponse createUser(String url, Endpoints endpoint, CreateUserRequest user, String token) {
        LOGGER.info("Создание пользователя");

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(user)
                .post(url + endpoint.getEndpoint())
                .then()
                .log().all()
                .extract().as(CreateUserResponse.class);
    }

    // Получение всех пользователей из базы данных
    public static List<UsersResponse> getAllUsers() {
        LOGGER.info("Получение всех пользователей из базы данных");

        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(USERS_ENDPOINT)
                .then()
                .extract().body().jsonPath().getList(".", UsersResponse.class);
    }

    // Удаление пользователя
    public static Response deleteUserById(String url, Endpoints endpoint, String token, int userId) {
        LOGGER.info("Удаление пользователя по ID");

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete(url + endpoint.getEndpoint() + "/" + userId)
                .then()
                .log().all()
                .extract().response();
    }
}
