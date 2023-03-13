package order;

import base.ScooterRent;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderSteps extends ScooterRent {
    public final static String ORDER_URI = BASE_URI + "orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseReqSpec())
                .body(order)
                .when()
                .post(ORDER_URI)
                .then();
    }

}
