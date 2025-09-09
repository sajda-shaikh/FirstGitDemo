package POJOTestingSerialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddPlaceParentPOJO AP = new AddPlaceParentPOJO();
		LocationPOJO LP = new LocationPOJO();
		LP.setLat(-38.383494);
		LP.setLng(33.427362);
		
		List<String> t = new ArrayList<>();
		t.add("shoe park");
		t.add("shop");
		
		AP.setAccuracy(50);
		AP.setName("Frontline house");
		AP.setPhone_number("(+91) 983 893 3937");
		AP.setAddress("29, side layout, cohen 09");
		AP.setWebsite("http://google.com");
		AP.setLanguage("French-IN");
		AP.setLocation(LP);
		AP.setTypes(t);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification resp = given().spec(req).body(AP);
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlaceResponsePayload response = resp.when().post("/maps/api/place/add/json")
						.then().log().all().spec(res).extract().as(AddPlaceResponsePayload.class);
		//System.out.println(response);
				
		String PlaceID = response.getPlace_id();
		
		System.out.print(PlaceID);

	}

}
