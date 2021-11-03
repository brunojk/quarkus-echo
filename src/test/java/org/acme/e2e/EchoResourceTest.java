package org.acme.e2e;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class EchoResourceTest {

    @Test
    public void testEchoEndpoint() {

        final String word = "bruno";

        given()
            .when().get("/echo/" + word)
            .then()
                .statusCode(200)
                .body(
                    "tag", equalTo("default_quarkus"),
                    "uuid", notNullValue(),
                    "word", equalTo(word)
                );

    }

}