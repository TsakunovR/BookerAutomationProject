package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.clients.APIClient;
import core.models.Booking;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class GetBookingTest {

    private APIClient apiClient;
    private ObjectMapper objectMapper;

    // Инициализация API клиента перед каждым тестом
    @BeforeEach
    public void setup() {
        apiClient = new APIClient();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetBooking() throws Exception {
        step("Выполняем запрос к эндпоинту /booking через APIClient");
        Response response = apiClient.getBooking();

        step("Проверяем, что статус-код ответа равен 200");
        assertThat(response.getStatusCode()).isEqualTo(200);

        step("Десериализуем тело ответа в список объектов Booking");
        String responseBody = response.getBody().asString();
        List<Booking> bookings = objectMapper.readValue(responseBody, new TypeReference<List<Booking>>() {});

        step("Проверяем, что тело ответа содержит объекты Booking");
        assertThat(bookings).isNotEmpty(); // Проверяем, что список не пуст

        step("Проверяем, что каждый объект Booking содержит валидное значение bookingid");
        for (Booking booking : bookings) {
            assertThat(booking.getBookingid()).isGreaterThan(0); // bookingid должен быть больше 0
        }
    }
}
