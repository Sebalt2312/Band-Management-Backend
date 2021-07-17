package org.acme.rest.client.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class FestivalResourceTest {

  @Test
  void getAll() {
    given().when()
      .contentType(ContentType.JSON)
      .body("{ \"query\":\"{\n" +
                "festivals { \n" +
                "      id \n" +
                "      name\n" +
                "      startDate\n" +
                "      endDate\n" +
                "      bands {\n" +
                "          id\n" +
                "          name\n" +
                "          genre\n" +
                "      }\n" +
                "     }\n" +
                "  }\"\n" +
                "}")
      .post("/graphql")
      .then()
      .body("data.festivals", Matchers.not(Matchers.emptyArray()))
      .statusCode(200);
  }

  @Test
  void findByFestivalName() {
  }

  @Test
  void addBandToFestival() {
  }
}
