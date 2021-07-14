package org.acme.rest.client.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


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
      given().when()
        .contentType(ContentType.JSON)
        .body("{ \"query\":\"{\n" +
          " findByMemberName(name: \\\"Campino\\\"){" +
          "   id" +
          "   name" +
          "   instrument" +
          "  }" +
          " }\"" +
          "}"
        )
        .post("/graphql")
        .then()
        .body("data.findByMemberName.name", is("Campino"))
        .statusCode(200);
    }

    @Test
    void findByBand() {
      given().when()
        .contentType(ContentType.JSON)
        .body("{ \"query\":\"{\n" +
          " findByBand(bandName: \\\"Die Ärzte\\\"){" +
          "   id" +
          "   name" +
          "   instrument" +
          "  }" +
          " }\"" +
          "}"
        )
        .post("/graphql")
        .then()
        .body("data.findByBand", not(emptyArray()))
        .statusCode(200);
    }

    @Test
    void createMember() {
        given().when()
                .contentType(ContentType.JSON)
                .body("{ \"query\": \"mutation{" +
                        "  createMember(name: \\\"Andi\\\", instrument: \\\"Baseguitar\\\")\n" +
                        "}\"" +
                        "}")
                .post("/graphql")
                .then()
                .body("data.createMember", is("Andi"))
                .statusCode(200);
    }

    @Test
    void addBand() {
      given().when()
        .contentType(ContentType.JSON)
        .body("{ \"query\": \"mutation{" +
          "  addBand(name: \\\"Andrew\\\", bandName: \\\"Donots\\\")\n" +
          "}\"" +
          "}")
        .post("/graphql")
        .then()
        .body("data.addBand", is("Donots"))
        .statusCode(200);
    }
}
