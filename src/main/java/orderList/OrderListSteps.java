package orderList;

import base.ScooterRent;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class OrderListSteps extends ScooterRent {
    public final static String ORDER_URI = BASE_URI + "orders";


    @Step("Получение списка заказов")
    public static OrderList checkOrderList() {
        return given()
                .spec(getBaseReqSpec())
                .get(ORDER_URI)
                .body()
                .as(OrderList.class);
    }
}
