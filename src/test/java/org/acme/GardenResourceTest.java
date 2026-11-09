package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class GardenResourceTest {

    @Test
    void testGetGardenById() {
        given()
            .when()
                .get("/gardens/1")
            .then()
                .statusCode(200)
                .body("address", equalTo("Flurstraße 17, 4470 Enns"));
    }
}
