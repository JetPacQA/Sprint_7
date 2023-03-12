package courier;

import base.ScooterRent;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierSteps extends ScooterRent {
    public final static String COURIER_URI = BASE_URI + "courier/";


    @Step ("Создание курьера")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseReqSpec())
                .body(courier)
                .when()
                .post(COURIER_URI)
                .then();
    }

    @Step ("Логин под курьером")
    public ValidatableResponse login(Courier courierLogin){
        return given()
                .spec(getBaseReqSpec())
                .body(courierLogin)
                .when()
                .post(COURIER_URI + "login/")
                .then();
    }

    @Step("Получение ID курьера")
    public static Id getCourierId(Courier courierLogin) {
        return given()
                .spec(getBaseReqSpec())
                .body(courierLogin)
                .post(COURIER_URI +"login/")
                .body()
                .as(Id.class);
    }

    @Step ("Удаление курьера по ID")
    public static ValidatableResponse deleteCourierById(int courierId){
        return given()
                .spec(getBaseReqSpec())
                .when()
                .delete(COURIER_URI + courierId)
                .then();
    }
}
