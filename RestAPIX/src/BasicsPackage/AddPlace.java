package BasicsPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class AddPlace {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(PayloadTest.AddPlace()).
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().asString();

		System.out.println("API Response=" + response);
		
		JsonPath js = new JsonPath(response);
		
		String Place_ID = js.getString("place_id");
		
		System.out.println("Place ID = " + Place_ID);
		
		String NewAddress = "Summer Walk";
		
		
		String updateplace = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(ResponsePayloadTest.GetPlace(Place_ID, NewAddress)).
		when().put("/maps/api/place/update/json").
		then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().asString();
		System.out.println("Update place" +updateplace);
		JsonPath js1 = new JsonPath(updateplace);
		
		String UpdateMessage = js1.getString("msg");
		
		System.out.println("Update Message = " + UpdateMessage);

//		
//		
		String getPlace = given().queryParam("key", "qaclick123").queryParam("place_id", Place_ID).
		when().get("/maps/api/place/get/json").
		then().assertThat().statusCode(200).extract().asString();
		System.out.println("Get PlaceID = " +getPlace);
		
		JsonPath js2 = new JsonPath(getPlace);
		String ActualAddress = js2.getString("address");
		
		System.out.println("Actual Address = " + ActualAddress);
		
		Assert.assertEquals(ActualAddress, NewAddress);
		System.out.println("Address updated with the new address");

	}


}
