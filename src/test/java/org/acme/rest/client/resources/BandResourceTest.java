package org.acme.rest.client.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import io.restassured.assertion.*;

@QuarkusTest
class BandResourceTest {

    @Test
    void getAll() {
      RestAssured.given()
        .when().body("{ \"query\" \"{\n" +
        "  \\\"bands\\\" {\n" +
        "    id\n" +
        "    bandName\n" +
        "    genre\n" +
        "  }\n" +
        " }\"\n" +
        "}").log().body().post("/graphql")
        .then().statusCode(200);
    }

    @Test
    void findByBandName() {
    }

    @Test
    void createBand() {
    }

    @Test
    void bandCreated() {
    }
}
