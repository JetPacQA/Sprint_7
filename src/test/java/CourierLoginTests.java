import courier.Courier;
import courier.CourierGenerator;
import courier.CourierStatuses;
import courier.CourierSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class CourierLoginTests {
    private final CourierSteps courierSteps = new CourierSteps();
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierStatuses status = new CourierStatuses();
    private int id;

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @After
    public void deleteCourier() {
        CourierSteps.deleteCourierById(id);
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    @Description("В данном сценарии проверяется, что работает авторизация с валидными данными")
    public void courierCanBeLoggedIn() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse = courierSteps.create(courier);
        status.createdSuccessfully(createCourierResponse);
        ValidatableResponse loginCourierResponse = courierSteps.login(courier);
        status.loggedInSuccessfully(loginCourierResponse);
        id = CourierSteps.getCourierId(courier).getId();
    }

    @Test
    @DisplayName("Авторизация без логина")
    @Description("В данном сценарии проверяется, что нельзя выполнить авторизацию без логина")
    public void sighInWithoutLogin() {
        Courier courier = generator.random();
        courier.setLogin(null);
        ValidatableResponse loginCourierResponse = courierSteps.login(courier);
        status.loginFailed(loginCourierResponse);
    }

    @Test
    @DisplayName("Авторизация без пароля")
    @Description("В данном сценарии проверяется, что нельзя выполнить авторизацию без пароля")
    public void sighInWithoutPassword() {
        Courier courier = generator.random();
        courier.setPassword(null);
        ValidatableResponse loginCourierResponse = courierSteps.login(courier);
        status.loginFailed(loginCourierResponse);
    }

    @Test
    @DisplayName("Авторизация с неверным логином")
    @Description("В данном сценарии проверяется, что нельзя выполнить авторизацию с неверным логином")
    public void sighInWrongLogin() {
        Courier courier = generator.random();
        courier.setLogin("000000");
        ValidatableResponse loginCourierResponse = courierSteps.login(courier);
        status.accountNotFound(loginCourierResponse);
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    @Description("В данном сценарии проверяется, что нельзя выполнить авторизацию с неверным паролем")
    public void sighInWrongPassword() {
        Courier courier = generator.random();
        courier.setPassword("000000");
        ValidatableResponse loginCourierResponse = courierSteps.login(courier);
        status.accountNotFound(loginCourierResponse);
    }

}
