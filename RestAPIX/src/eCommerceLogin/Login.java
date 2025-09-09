package eCommerceLogin;
import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoginRequestPOJO LoginReq = new LoginRequestPOJO();
		//LoginResponsePOJO LoginRes = new LoginResponsePOJO();
		
		LoginReq.setUserEmail("sajda@gmail.com");
		LoginReq.setUserPassword("Sda123!@");
		
		RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com").build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();
			
		LoginResponsePOJO response = given().spec(req).body(LoginReq).log().all()
				.when().post("/api/ecom/auth/login")
				.then().spec(res).log().all().extract().response().as(LoginResponsePOJO.class);
		
		String Logintoken;
		String UserID;
		
		Logintoken = response.getToken();
		UserID = response.getUserId();
		
		System.out.println("Access Token = " +Logintoken);
		System.out.println("User ID = " +UserID);

		
	}

}
