package GraphQL;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class graphQLTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Query
		int charId = 17358;
		String response = given().log().all().header("Content-Type", "application/json").body("{\n"
				+ "  \"query\": \"query($characterId: Int!, $locationId: Int!)\\n{\\n  character(characterId: $characterId) \\n  {\\n    name\\n    gender\\n    status\\n    id\\n    type\\n    location {\\n      id\\n    }\\n  }\\n  location(locationId:$locationId)\\n  {\\n    name\\n    dimension\\n    type\\n    created\\n  }\\n  episode(episodeId:16383)\\n  {\\n    episode\\n    name\\n    air_date\\n  }\\n  characters(filters:{name:\\\"char\\\"})\\n  {\\n    info\\n    {\\n      count\\n      pages\\n    }\\n    result\\n    {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters:{episode:\\\"episode\\\"})\\n  {\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n  \\n}\\n\",\n"
				+ "  \"variables\": {\n"
				+ "    \"characterId\": "+charId+",\n"
				+ "    \"locationId\": 24142\n"
				+ "  }\n"
				+ "}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		
		String charname = js.getString("data.character.name");
		
		System.out.println(charname);
		
		Assert.assertEquals(charname, "NewCharacterName");
		
		//mutation
		String mutationresponse = given().log().all().header("Content-Type", "application/json").body("{\n"
				+ "  \"query\": \"mutation($locationName: String!, $characterName: String!, $episodeName: String!)\\n{\\n  createLocation(location: {name:$locationName,type:\\\"TestUKtype\\\",dimension:\\\"testUKdimensio\\\"})\\n  {\\n    id\\n  }\\n  \\n  createCharacter(character: {name:$characterName, type:\\\"CharType\\\", status:\\\"charStatus\\\", species:\\\"charSpecies\\\",gender:\\\"charGender\\\", image:\\\"charimage\\\", originId:24141, locationId:24140})\\n  {\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName, air_date:\\\"28/01/2020\\\",episode:\\\"Test episode\\\"})\\n  {\\n    id\\n  }\\n  deleteLocations(locationIds:[24129])\\n  {\\n    locationsDeleted\\n  }\\n}\",\n"
				+ "  \"variables\": {\n"
				+ "    \"locationName\": \"NewLocation\",\n"
				+ "    \"characterName\": \"NewCharacterName\",\n"
				+ "    \"episodeName\": \"NewEpisodeName\"\n"
				+ "  }\n"
				+ "}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		

		System.out.println(mutationresponse);
		
		JsonPath js1 = new JsonPath(mutationresponse);
		
	}

}
