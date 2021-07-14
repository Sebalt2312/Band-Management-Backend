package org.acme.rest.client.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import io.restassured.assertion.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BandResourceTest {

    @Test
    void getAll() {
        RestAssured.given()
                .when().body("{ \"query\": \"{\n" +
                        "  bands {\n" +
                        "    id\n" +
                        "    bandName\n" +
                        "    genre\n" +
                        "    member {\n" +
                        "      id\n" +
                        "      name\n" +
                        "      instrument\n" +
                        "    }\n" +
                        "  }\n" +
                        " }\"\n" +
                        "}").log().body().post("/graphql")
                .then().statusCode(200);
    }

    @Test
    void getByBandName() {
        given().when()
                .contentType(ContentType.JSON)
                .body("{ \"query\":\"{\n" +
                        " findByName(bandName: \\\"Donots\\\"){" +
                        "   id" +
                        "   bandName" +
                        "   member {" +
                        "     id" +
                        "     name" +
                        "   }" +
                        "  }" +
                        " }\"" +
                        "}"
                )
                .post("/graphql")
                .then()
                .body("data.band.member", Matchers.not(Matchers.emptyArray()))
                .statusCode(200);
    }

    @Test
    void createBand() {
        given().when()
                .contentType(ContentType.JSON)
                .body("{ \"mutation\":\"{" +
                        " createBand(bandName: \\\"The Ghost Inside\\\", genre: \\\"Hardcore\\\")" +
                        "}\"" +
                        "}")
                .post("/graphql")
                .then()
                .statusCode(200)
                .body("data.createBand", Matchers.contains("Ghost"));
    }

    @Test
    void bandCreated() {
    }
}
