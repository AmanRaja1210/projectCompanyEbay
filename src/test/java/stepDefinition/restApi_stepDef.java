package stepDefinition;

import com.aventstack.extentreports.ExtentTest;
import hooks.ExtentHooks;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.openqa.selenium.remote.ResponseCodec;

public class restApi_stepDef {

    public RequestSpecification requestSpecification;
    public Response response;
    public  int responseCode;
    public ResponseBody responseBody;
    private final ExtentTest test = ExtentHooks.getTest();


    @Given("I hit the URL of get product endpoint")
    public void i_hit_the_url_of_get_product_endpoint() {

        RestAssured.baseURI="https://api.coindesk.com/";
        test.info("Base URL is set: "+RestAssured.baseURI);
        System.out.println("Base URL is: "+RestAssured.baseURI);

    }
    @When("I pass the URL of the product in the request")
    public void i_pass_the_url_of_the_product_in_the_request() {

        requestSpecification=RestAssured.given();
        response=requestSpecification.get("v1/bpi/currentprice.json");
        test.info("Sent GET request to v1/bpi/currentprice.json");
        System.out.println("Sent GET request to v1/bpi/currentprice.json");

    }
    @Then("I receive the response as {int}")
    public void i_receive_the_response_as(Integer expectedStatusCode) {
        responseCode =response.getStatusCode();
        test.info("The response code is: "+responseCode);
        System.out.println("The response code is: "+responseCode);
        Assert.assertEquals(responseCode,expectedStatusCode.intValue(),"Status code mismatch!");
    }


    @And("I verify the response contains  BPIs")
    public void iVerifyTheResponseContainsBPIs() {
        responseBody=response.getBody();
        JsonPath jsonPath= response.jsonPath();


        String bpiString= jsonPath.getJsonObject("bpi").toString();
        test.info("Extracted Response from BPI: "+bpiString);
        System.out.println("Extracted Response from BPI: "+bpiString);

        Assert.assertTrue(bpiString.contains("USD"),"BPI does not contain USD!");
        Assert.assertTrue(bpiString.contains("GBP"),"BPI does not contain GBP!");
        Assert.assertTrue(bpiString.contains("EUR"),"BPI does not contain EUR!");
        test.pass("Verified that BPI contains USD,GBP, and EUR");
    }

    @And("I verify that GBP description equals British Pound Sterling")
    public void iVerifyThatGBPDescriptionEqualsBritishPoundSterling() {

        responseBody=response.getBody();
        JsonPath jsonPath= response.jsonPath();

        String gbpDescription= jsonPath.getString("bpi.GBP.description");
        String expectedDescription="British Pound Sterling";
        Assert.assertEquals(gbpDescription,expectedDescription,"GBP! description does not match!");
        test.pass("Verified that GBP description equals: "+gbpDescription);
        System.out.println("Verified that GBP description equals: "+gbpDescription);

    }
}
