package org.acme.rest.client.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BandResourceTest {

    @Test
    void getAll() {
        given().when()
                .contentType(ContentType.JSON)
                .body("{ \"query\":\"{" +
                        " bands {\n" +
                        "    id\n" +
                        "    bandName\n" +
                        "    member {\n" +
                        "      id\n" +
                        "      name\n" +
                        "      instrument\n" +
                        "    }\n" +
                        "   } \n" +
                        "  }\"\n" +
                        "}" +
                        "}")
                .post("/graphql")
                .then()
                .body("data.bands", Matchers.not(Matchers.emptyArray()))
                .statusCode(200);
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
                .body("{ \"query\": \"mutation{" +
                        " createBand(bandName: \\\"The Ghost Inside\\\", genre: \\\"Hardcore\\\")" +
                        "}\"" +
                        "}")
                .post("/graphql")
                .then()
                .statusCode(200)
                .body("data.createBand", Matchers.not(Matchers.emptyArray()));
    }

    @Test
    void bandCreated() {
    }
}
