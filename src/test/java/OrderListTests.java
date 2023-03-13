import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import orderList.OrderListSteps;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderListTests {

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Test
    @DisplayName("Check Orders List")
    @Description("Проверка получения списка заказов")
    public void checkOrdersList() {
        OrderListSteps orderListSteps = new OrderListSteps();
        orderListSteps.checkOrderList();
    }
}
