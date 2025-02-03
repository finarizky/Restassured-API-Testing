package apiauto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.finarizky.Main;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DELETEScenario {

    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    public static RequestSpecification given() {
        return RestAssured.given();
    }

    @Test
    public void DELETEUser(){
        setup();

        //POST -> karena saat code ini gorest error tdk bisa menampilkan spesifik data
        HashMap<String, Object> postBody = new HashMap<>();
        postBody.put("name", "Satria Baja");
        postBody.put("email", "satria" + System.currentTimeMillis() + "@getnada.com");
        postBody.put("gender", "male");
        postBody.put("status", "active");

        JSONObject postJson = new JSONObject(postBody);

        Response postResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(postJson.toString())
                .when()
                .post("/users");

        postResponse.prettyPrint();

        int userid = postResponse.jsonPath().getInt("id");
        System.out.println(" USER CREATED ");

        //DELETE
        Response deleteResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .when()
                .delete("/users/" + userid);

        deleteResponse.prettyPrint();

        deleteResponse.then().assertThat()
                .statusCode(204);

        System.out.println(" SUCCESSFULLY DELETE USER");
    }

    @Test
    public void DELETEUserInvalidID(){

        setup();

        int id = 767518700;

        given().log().all()
                .when().delete("/users/" + id)
                .then().log().all().assertThat().statusCode(404);

        System.out.println(" FAILED AS EXPECTED ");
    }

}