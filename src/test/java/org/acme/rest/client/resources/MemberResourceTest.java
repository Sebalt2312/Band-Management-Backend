package org.acme.rest.client.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class MemberResourceTest {

    @Test
    void getAll() {
        given().when()
                .contentType(ContentType.JSON)
                .body("{ \"query\":\"{" +
                        " member {\n" +
                        "    id\n" +
                        "    name\n" +
                        "    instrument\n" +
                        "  }\"\n" +
                        "}" +
                        "}")
                .post("/graphql")
                .then()
                .body("data.member", Matchers.not(Matchers.emptyArray()))
                .statusCode(200);
    }

    @Test
    void getMember() {
    }

    @Test
    void findByBand() {
    }

    @Test
    void createMember() {
        given().when()
                .contentType(ContentType.JSON)
                .body("{ \"query\": \"mutation{" +
                        "  createMember(name: \\\"Andi\\\", instrument: \\\"Base\\\")\n" +
                        "}\"" +
                        "}")
                .post("/graphql")
                .then()
                .statusCode(200);
    }

    @Test
    void addBand() {
    }
}