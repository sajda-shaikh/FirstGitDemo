package JiraTesting;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Jira {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://sajdashaikh.atlassian.net";
		String response = given().header("Authorization", "Basic c2FqZGEuc2toQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAzaXRTMU1XSExFTDNaVjhMYjZaUnhGWUtCZTRCV3lIRkZXYThlSDgtSkU0R3lXQjNORWxJbFdGUmVLNmdOeDI3NHZzcS12dG5WYVB1bjhtSW1fX1JvMGNId01hTGV3WDRNdFlDZ1diWWZRQ0NCUnJWWUVkbEI4c0pYcThlQzFGeTFrWUFjWUk3RmlVUzluNTlWZmRISl9SWGoyVEttOG9vVDlyWEVNa3pmbzA9QzM4QTQxQ0Q=").header("Content-Type", "application/json").body(JiraPayload.JiraCreateIssuePayload()).
		when().post("/rest/api/3/issue").
		then().assertThat().statusCode(201).header("Server", "AtlassianEdge").extract().asString();

		System.out.println("API Response=" + response);
		
		JsonPath js = new JsonPath(response);
		
		String Issue_ID = js.getString("id");
		System.out.println("Jira Issue ID = " + Issue_ID);
		
		//Add attachment in the created issue
		
		File file=new File("/Users/sajda/Documents/Generative AI for Testing/requirements_ai.docx");
		String response1 = given().pathParam("JiraIssueID", Issue_ID).header("Authorization", "Basic c2FqZGEuc2toQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAzaXRTMU1XSExFTDNaVjhMYjZaUnhGWUtCZTRCV3lIRkZXYThlSDgtSkU0R3lXQjNORWxJbFdGUmVLNmdOeDI3NHZzcS12dG5WYVB1bjhtSW1fX1JvMGNId01hTGV3WDRNdFlDZ1diWWZRQ0NCUnJWWUVkbEI4c0pYcThlQzFGeTFrWUFjWUk3RmlVUzluNTlWZmRISl9SWGoyVEttOG9vVDlyWEVNa3pmbzA9QzM4QTQxQ0Q=").header("Content-Type", "application/json")
				.header("X-Atlassian-Token", "no-check")
				.header("Content-Type", "multipart/form-data")
				.multiPart(file)
				.when().post("/rest/api/3/issue/{JiraIssueID}/attachments").
				then().assertThat().statusCode(200).extract().asString();

				System.out.println("API Response=" + response1);
				
				JsonPath js1 = new JsonPath(response1);
				
				String attachment = js1.getString("filename");
				System.out.println("File Attachment = " + attachment);
	
		
	}

}
