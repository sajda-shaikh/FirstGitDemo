package OAuthTesting;

import static io.restassured.RestAssured.given;

import java.util.List;

import POJOTestingDeserialization.ApiPOJO;
import POJOTestingDeserialization.GetCourseParentResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ClientCredentialsTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type", "client_credentials")
				.formParams("scope", "trust")
		.when().post("/oauthapi/oauth2/resourceOwner/token").
		then().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").extract().asString();

		System.out.println("API Response=" + response);
		
		JsonPath js = new JsonPath(response);
		
		String AccessToken = js.getString("access_token");
		System.out.println("Client Credentials Access Token is = " + AccessToken);
		
		GetCourseParentResponse response1 = given().queryParams("access_token", AccessToken)
				.when().get("/oauthapi/getCourseDetails").
				then().assertThat().statusCode(401).header("Server", "Apache/2.4.52 (Ubuntu)").extract().as(GetCourseParentResponse.class);
		
		System.out.print(response1);
		System.out.println("API Course Title = " + response1.getCourses().getApi().get(0).getCourseTitle());
		
		List<ApiPOJO> APICourses = response1.getCourses().getApi();
		int size = APICourses.size();
		int i;
		for(i=0; i<size; i++)
		{
			System.out.println(APICourses.get(i).getCourseTitle());
			//if(APICourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
			//{
				System.out.println(APICourses.get(i).getPrice());
			//}
		}
		

	}

}
