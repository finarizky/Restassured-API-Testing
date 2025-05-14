package apiauto;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.finarizky.Main;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

public class PATCHScenario {

    @Test(description = "Verify patch scenario")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies the patch user passed")

    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    public static RequestSpecification given() {
        return RestAssured.given();
    }

    @Test
    public void PATCHUserName(){

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

        int userId = postResponse.jsonPath().getInt("id");
        System.out.println(" USER CREATED ");

        //PATCH
        String newName = "Satria Baja Hitam Updated";

        postBody.put("name", newName);

        JSONObject patchJson = new JSONObject(postBody);

        Response patchResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(patchJson.toString())
                .when()
                .patch("/users/" + userId);

        patchResponse.prettyPrint();

        patchResponse.then().assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(newName));

        System.out.println(" NAME SUCCESSFULLY UPDATED TO : " + newName);

    }

    @Test
    public void PATCHUserEmail(){

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

        int userId = postResponse.jsonPath().getInt("id");
        System.out.println(" USER CREATED ");

        //PATCH
        String newemail = "satriabajahitamnew" + System.currentTimeMillis() + "@getnada.com";

        postBody.put("email", newemail);

        JSONObject patchJson = new JSONObject(postBody);

        Response patchResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(patchJson.toString())
                .when()
                .patch("/users/" + userId);

        patchResponse.prettyPrint();

        patchResponse.then().assertThat()
                .statusCode(200)
                .body("email", Matchers.equalTo(newemail));

        System.out.println(" EMAIL SUCCESSFULLY UPDATED TO : " + newemail);

    }

    @Test
    public void PATCHUserGender(){

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

        int userId = postResponse.jsonPath().getInt("id");
        System.out.println(" USER CREATED ");

        //PATCH
        String newgender = "female";

        postBody.put("gender", newgender);

        JSONObject patchJson = new JSONObject(postBody);

        Response patchResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(patchJson.toString())
                .when()
                .patch("/users/" + userId);

        patchResponse.prettyPrint();

        patchResponse.then().assertThat()
                .statusCode(200)
                .body("gender", Matchers.equalTo(newgender));

        System.out.println(" STATUS SUCCESSFULLY UPDATED TO : " + newgender);

    }

    @Test
    public void PATCHUserStatus(){

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

        int userId = postResponse.jsonPath().getInt("id");
        System.out.println(" USER CREATED ");

        //PATCH
        String newstatus = "inactive";

        postBody.put("status", newstatus);

        JSONObject patchJson = new JSONObject(postBody);

        Response patchResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(patchJson.toString())
                .when()
                .patch("/users/" + userId);

        patchResponse.prettyPrint();

        patchResponse.then().assertThat()
                .statusCode(200)
                .body("status", Matchers.equalTo(newstatus));

        System.out.println(" STATUS SUCCESSFULLY UPDATED TO : " + newstatus);

    }

    @Test
    public void PATCHWrongID() {

        setup();

        int id = 76751870;
        String Newname = "satria";

        Response response = given().when().get("/users/" + id);
        System.out.println(response.getBody().asString());

        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("id", id);
        bodyMap.put("name", Newname);

        JSONObject jsonObject = new JSONObject(bodyMap);


        given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .body(jsonObject.toString())
                .put("/users/" + id)
                .then().log().all()
                .assertThat().statusCode(404);

        System.out.println(" FAILED AS EXPECTED ");
    }

}
