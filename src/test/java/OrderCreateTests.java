import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import order.Order;
import order.OrderStatuses;
import order.OrderSteps;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTests {
    private List<String> color;

    public OrderCreateTests(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] colorVariants() {
        return new Object[][]{
                {List.of("GRAY")},
                {List.of("BLACK")},
                {List.of("GRAY", "BLACK")},
                {List.of("")},
        };
    }

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    private final OrderStatuses status = new OrderStatuses();

    @Test
    @DisplayName("Создание заказа")
    @Description("В данном сценарии проверяется создание заказа с первым цветом, " +
            "со вторым цветом, с двумя цветами, без указания цвета")
    public void createOrders() {
        OrderSteps orderSteps = new OrderSteps();
        ValidatableResponse createOrderResponse = orderSteps.create(new Order("Иван", "Иванов",
                "улица Пушкина, дом Колотушкина, кв. 1", "Пушкинская", "+7 111 222 33 44", 5,
                "01-05-2023", "Позвонить перед доставкой", color));
        status.createdSuccessfully(createOrderResponse);
    }


}
