package ghofur.stepdef.apiauto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import io.restassured.RestAssured;
import java.io.File;

import static io.restassured.RestAssured.given;

public class TestReqres {

    private String Valuename;
    private String Valuejob;
    private Response response; // Variabel untuk menyimpan respons

    @Given("I send a GET request to list users")
    public void testGetListUsers() {
        File jsonSchema = new File("src/test/resources/jsonSchema/getListUserSchema.json");
        response = given().when() // Simpan respons ke variabel `response`
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("per_page", Matchers.equalTo(6))
                .assertThat().body("page", Matchers.equalTo(2))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema))
                .extract().response(); // Ekstrak respons untuk digunakan di step berikutnya
    }

    @Given("I set name to {string} and job to {string}")
    public void setUserData(String name, String job) {
        this.Valuename = name;
        this.Valuejob = job;
    }

    @Given("I send a POST request to create a user")
    public void testPostCreateUser() {
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("name", Valuename);
        bodyObj.put("job", Valuejob);

        response = given() // Simpan respons ke variabel `response`
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bodyObj.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("name", Matchers.equalTo(Valuename))
                .extract().response();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the response should contain {string} with value {int}")
    public void verifyResponseBodyInt(String key, int value) {
        response.then().body(key, Matchers.equalTo(value));
    }

    @Then("the response should contain {string} with value {string}")
    public void verifyResponseBodyString(String key, String value) {
        response.then().body(key, Matchers.equalTo(value));
    }
}