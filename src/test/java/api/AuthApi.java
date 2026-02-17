package api;

import io.restassured.http.ContentType;
import model.User;

import static io.restassured.RestAssured.given;

public class AuthApi {

    private static final String BASE_URL = "https://qa-desk.stand.praktikum-services.ru/api";

    // Метод регистрации пользователя
    public static void register(User user) {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user) // Lombok сам преобразует объект в JSON благодаря геттерам
                .when()
                .post("/signup")
                .then()
                .statusCode(201); // Проверяем, что пользователь создан
    }

    // Метод логина
    public static String loginAndGetToken(User user) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/signin")
                .then()
                .statusCode(201)
                .extract()
                .path("token.access_token"); // Извлекаем токен из ответа
    }
}
