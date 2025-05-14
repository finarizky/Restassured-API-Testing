package apiauto;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.finarizky.Main;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


public class GETScenario {

    @Test(description = "Verify get scenario")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies the get user passed")

    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    @Test
    public void GetAllData() {

        setup();

        File jsonSchema = new File("src/test/resources/jsonschema/getlistuserschema");

        RestAssured.given().when().get("/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

    }

    @Test
    public void GetDataMale() {

        setup();

        File jsonSchema = new File("src/test/resources/jsonschema/getlistuserschema");

        RestAssured.given()
                .queryParam("gender", "male")
                .when().get("/users")
                .then().log().all()
                .assertThat().body("gender", hasItem("male"))
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

        System.out.println(" SUCCESSFULLY GET ALL DATA ");

    }

    @Test
    public void GetDataStatusActive() {

        setup();

        File jsonSchema = new File("src/test/resources/jsonschema/getlistuserschema");

        RestAssured.given()
                .queryParam("status", "active")
                .when().get("/users")
                .then().log().all()
                .assertThat().body("status", hasItem("active"))
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

        System.out.println(" SUCCESSFULLY GET ACTIVE DATA ");

    }

    @Test
    public void GetDataSpesific() {

        int id = 7675187;

        setup();

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .queryParam("id", id)
                .when().get("/users/"+id)
                .then().log().all()
                .assertThat().body("id", equalTo(id))
                .assertThat().statusCode(200);

        System.out.println(" SUCCESSFULLY GET SPECIFIC DATA ");

    }

    @Test
    public void GetDataNotFound() {

        int id = 999999999;

        setup();

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .queryParam("id", id)
                .when().get("/users/"+id)
                .then().log().all()
                .assertThat().body("message", equalTo("Resource not found"))
                .assertThat().statusCode(404);

        System.out.println(" FAILED AS EXPECTED ");

    }

    @Test
    public void GetDataPagination() {

        setup();

        File jsonSchema = new File("src/test/resources/jsonschema/getlistuserschema");

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .param("per_page", 3)
                .when().get("/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data.size", equalTo(3))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

        System.out.println(" SUCCESSFULLY GET DATA ");
    }


}
         