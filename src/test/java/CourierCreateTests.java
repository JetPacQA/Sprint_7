import courier.*;
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


public class CourierCreateTests {
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
    @DisplayName("Курьер может быть создан")
    @Description("В данном сценарии проверяется, что курьер может быть создан с валидными значениями")
    public void courierCanBeCreatedWithValidData() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse = courierSteps.create(courier);
        status.createdSuccessfully(createCourierResponse);
        id = CourierSteps.getCourierId(courier).getId();
    }

    @Test
    @DisplayName("Курьер может быть создан без имени")
    @Description("В данном сценарии проверяется, что можно создать курьера без имени")
    public void checkCreateCourierWithoutName() {
        Courier courier = generator.random();
        courier.setFirstName(null);
        ValidatableResponse createCourierWithoutNameResponse = courierSteps.create(courier);
        status.createdSuccessfully(createCourierWithoutNameResponse);
        id = CourierSteps.getCourierId(courier).getId();
    }

    @Test
    @DisplayName("Нельзя создать одинаковых курьеров")
    @Description("В данном сценарии проверяется, что нельзя создать двух одинаковых курьеров")
    public void checkCreateDuplicateCourier() {
        Courier courier = generator.random();
        ValidatableResponse createCourierResponse = courierSteps.create(courier);
        status.createdSuccessfully(createCourierResponse);
        courier.getLogin();
        ValidatableResponse createCourierDuplicate = courierSteps.create(courier);
        status.loginAlreadyInUse(createCourierDuplicate);
        id = CourierSteps.getCourierId(courier).getId();
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    @Description("В данном сценарии проверяется, что нельзя создать курьера без логина")
    public void checkCreateCourierWithoutLogin() {
        Courier courier = generator.random();
        courier.setLogin(null);
        ValidatableResponse createCourierWithoutLoginResponse = courierSteps.create(courier);
        status.creationFailed(createCourierWithoutLoginResponse);
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    @Description("В данном сценарии проверяется, что нельзя создать курьера без пароля")
    public void checkCreateCourierWithoutPassword() {
        Courier courier = generator.random();
        courier.setPassword(null);
        ValidatableResponse createCourierWithoutPasswordResponse = courierSteps.create(courier);
        status.creationFailed(createCourierWithoutPasswordResponse);
    }

}

