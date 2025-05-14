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

public class PUTScenario {

    @Test(description = "Verify put scenario")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies the put user passed")

    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    public static RequestSpecification given() {
        return RestAssured.given();
    }

    @Test
    public void PUTNameUser(){

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

        //PUT
        String newName = "Satria Baja Updated";

        HashMap<String, Object> putBody = new HashMap<>();
        putBody.put("name", newName);
        putBody.put("email", postBody.get("email")); // Email tetap sama
        putBody.put("gender", postBody.get("gender"));
        putBody.put("status", postBody.get("status"));

        JSONObject putJson = new JSONObject(putBody);

        Response putResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(putJson.toString())
                .when()
                .put("/users/" + userId);

        Response getResponse = given()
                .header("Authorization", "Bearer " + Main.token)
                .when()
                .get("/users/" + userId);

        putResponse.prettyPrint();

        getResponse.then().assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo(newName));

        System.out.println(" NAME SUCCESSFULLY UPDATED TO : " + newName);

    }

    @Test
    public void PUTEmailUser(){

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

        //PUT
        String newemail = "satrianew" + System.currentTimeMillis() + "@getnada.com";

        HashMap<String, Object> putBody = new HashMap<>();
        putBody.put("name", postBody.get("name"));
        putBody.put("email", newemail);
        putBody.put("gender", postBody.get("gender"));
        putBody.put("status", postBody.get("status"));

        JSONObject putJson = new JSONObject(putBody);

        Response putResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(putJson.toString())
                .when()
                .put("/users/" + userId);

        Response getResponse = given()
                .header("Authorization", "Bearer " + Main.token)
                .when()
                .get("/users/" + userId);

        getResponse.then().assertThat()
                .statusCode(200)
                .body("email", Matchers.equalTo(newemail));

        System.out.println(" EMAIL SUCCESSFULLY UPDATED TO : " + newemail);

    }

    @Test
    public void PUTGenderUser(){

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

        //PUT
        String newgender = "female";

        HashMap<String, Object> putBody = new HashMap<>();
        putBody.put("name", postBody.get("name"));
        putBody.put("email", postBody.get("email"));
        putBody.put("gender", newgender);
        putBody.put("status", postBody.get("status"));

        JSONObject putJson = new JSONObject(putBody);

        Response putResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(putJson.toString())
                .when()
                .put("/users/" + userId);

        Response getResponse = given()
                .header("Authorization", "Bearer " + Main.token)
                .when()
                .get("/users/" + userId);

        getResponse.then().assertThat()
                .statusCode(200)
                .body("gender", Matchers.equalTo(newgender));

        System.out.println(" GENDER SUCCESSFULLY UPDATE TO : " + newgender);

    }

    @Test
    public void PUTStatusUser(){

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

        //PUT
        String newstatus = "inactive";

        HashMap<String, Object> putBody = new HashMap<>();
        putBody.put("name", postBody.get("name"));
        putBody.put("email", postBody.get("email"));
        putBody.put("gender", postBody.get("gender"));
        putBody.put("status", newstatus);

        JSONObject putJson = new JSONObject(putBody);

        Response putResponse = given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .contentType("application/json")
                .body(putJson.toString())
                .when()
                .put("/users/" + userId);

        Response getResponse = given()
                .header("Authorization", "Bearer " + Main.token)
                .when()
                .get("/users/" + userId);

        getResponse.then().assertThat()
                .statusCode(200)
                .body("status", Matchers.equalTo(newstatus));

        System.out.println(" STATUS SUCCESSFULLY UPDATE TO : " + newstatus);

    }

    @Test
    public void PUTWrongID(){

        setup();

        int id = 76751870;
        String Newname = "satria";

        String Oldnama = given().when().get("/users/"+id).getBody().jsonPath().get("name");
        String email = given().when().get("/users/"+id).getBody().jsonPath().get("data.email");
        String gender = given().when().get("/users/"+id).getBody().jsonPath().get("data.gender");
        String status = given().when().get("/users/"+id).getBody().jsonPath().get("data.status");

        Response response = given().when().get("/users/" + id);
        System.out.println(response.getBody().asString());

        HashMap<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("id", id);
        bodyMap.put("name", Newname);
        bodyMap.put("email", email);
        bodyMap.put("gender", gender);
        bodyMap.put("status", status);

        JSONObject jsonObject = new JSONObject(bodyMap);


        given().log().all()
                .header("Authorization", "Bearer " + Main.token)
                .body(jsonObject.toString())
                .put("/users/"+id)
                .then().log().all()
                .assertThat().statusCode(404);

        System.out.println(" FAILED AS EXPECTED ");


    }


}

