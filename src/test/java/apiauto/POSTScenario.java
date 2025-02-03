package apiauto;

import io.restassured.RestAssured;
import org.finarizky.Main;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class POSTScenario {

    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json");
    }


    @Test
    public void SuccessfullyPostUser(){

        setup();

        String name = "brodia";
        String email = "brodia" + System.currentTimeMillis() + "@getnada.com";
        String gender = "male";
        String status = "Active";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("email", Matchers.equalTo(email));

        System.out.println(" SUCCESSFULLY CREATED USER ");
    }

    @Test
    public void PostUserWithSpecialCharacters(){

        setup();

        String name = "Chilla123!";
        String email = "Chilla" + System.currentTimeMillis() + "@getnada.com";
        String gender = "male";
        String status = "Active";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("email", Matchers.equalTo(email));

        System.out.println(" SUCCESSFULLY CREATED USER ");
    }

    @Test
    public void PostUserWithLongName(){

        setup();

        String name = "Lorem ipsum dolor sit amet, consectetur adipiscing.";
        String email = "lidsac" + System.currentTimeMillis() + "@getnada.com";
        String gender = "male";
        String status = "Active";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("email", Matchers.equalTo(email));

        System.out.println(" SUCCESSFULLY CREATED USER ");
    }

    @Test
    public void PostUserWithINTName(){

        setup();

        String name = "456789000";
        String email = "kko" + System.currentTimeMillis() + "@getnada.com";
        String gender = "male";
        String status = "Active";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("email", Matchers.equalTo(email));

        System.out.println(" SUCCESSFULLY CREATED USER ");
    }


    @Test
    public void PostUserSameEmail(){

        setup();

        String name = "Chillo";
        String email = "chillo@getnada.com";
        String gender = "male";
        String status = "inactive";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("[0].field", equalTo("email"))
                .assertThat().body("[0].message", equalTo("has already been taken"));

        System.out.println(" FAILED AS EXPECTED ");
    }

    @Test
    public void PostUserInvalidToken(){

        setup();

        String name = "bunga";
        String email = "kembang" + System.currentTimeMillis() + "@getnada.com";
        String gender = "male";
        String status = "inactive";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + "1234431")
                .body(bodyObj.toString())
                .when()
                .post("/v2/users")
                .then().log().all()
                .assertThat().statusCode(404);

        System.out.println(" FAILED AS EXPECTED ");
    }

    @Test
    public void PostUserEmptyName() {

        setup();

        String name = "";
        String email = "silent" + System.currentTimeMillis() + "@getnada.com";
        String gender = "male";
        String status = "inactive";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("[0].field", equalTo("name"))
                .assertThat().body("[0].message", equalTo("can't be blank"));

        System.out.println(" FAILED AS EXPECTED ");
    }

    @Test
    public void PostUserInvalidEmail() {

        setup();

        String name = "mawar";
        String email = "mawarhitam2getnada.com";
        String gender = "male";
        String status = "inactive";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("[0].field", equalTo("email"))
                .assertThat().body("[0].message", equalTo("is invalid"));

        System.out.println(" FAILED AS EXPECTED ");
    }

    @Test
    public void PostUserExceptMOrF() {

        setup();

        String name = "mawar";
        String email = "mawarhita" + System.currentTimeMillis() + "@getnada.com";
        String gender = "Binary";
        String status = "inactive";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("[0].field", equalTo("gender"))
                .assertThat().body("[0].message", equalTo("can't be blank, can be male of female"));

        System.out.println(" FAILED AS EXPECTED ");
    }

    @Test
    public void PostUserNotActiveInactive() {

        setup();

        String name = "mawar";
        String email = "mawarhitam" + System.currentTimeMillis() + "@getnada.com";
        String gender = "female";
        String status = "single";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", name);
        bodyObj.put("email", email);
        bodyObj.put("gender", gender);
        bodyObj.put("status", status);

        RestAssured.given()
                .header("Authorization", "Bearer " + Main.token)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then().log().all()
                .assertThat().statusCode(422)
                .assertThat().body("[0].field", equalTo("status"))
                .assertThat().body("[0].message", equalTo("can't be blank"));

        System.out.println(" FAILED AS EXPECTED ");
    }

}
