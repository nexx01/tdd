package com.example.tdd;

import com.example.tdd.domain.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CarServise carService;

    @Test
    void getCar_shouldReturnCar() {
        given(carService.getCarsDetails(anyString())).willReturn(new Car("prius", "hybrid"));

        webTestClient.get()
                .uri("/cars/prius")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("prius")
                .jsonPath("type").isEqualTo("hybrid");
    }

    @Test
    void getCar_notFound() {
        given(carService.getCarsDetails(anyString()))
                .willThrow(new CarNotFoundException());

        webTestClient.get()
                .uri("/cars/prius")
                .exchange()
                .expectStatus().isNotFound();
    }
}
