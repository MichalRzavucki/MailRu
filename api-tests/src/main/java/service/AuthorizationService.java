package service;

import enums.Endpoints;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.AuthorizationRequest;
import pojo.AuthorizationResponse;

/**
 * Сервисный класс, отвечающий за авторизацию
 */
public class AuthorizationService {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationService.class);

    @Step("Получение токена авторизации")
    public static AuthorizationResponse getToken(String url, Endpoints endpoint, String login, String password) {
        LOGGER.info("Получение токена авторизации");
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(login, password);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(authorizationRequest)
                .post(url + endpoint.getEndpoint())
                .then()
                .log().all()
                .extract().response().as(AuthorizationResponse.class);
    }

    @Step("Получение токена авторизации и возврат статус-кода")
    public static Response getTokenWithStatus(String url, Endpoints endpoint, String login, String password) {
        LOGGER.info("Получение токена авторизации и возврат статус-кода");
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(login, password);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(authorizationRequest)
                .post(url + endpoint.getEndpoint())
                .then()
                .log().all()
                .extract().response();
    }
}
