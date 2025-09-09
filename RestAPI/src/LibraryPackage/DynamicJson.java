package LibraryPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJson {
  @Test(dataProvider="isbnaisledata")
  public void AddBook(String isbn, String aisle) {
	  
	  RestAssured.baseURI = "http://216.10.245.166";
	  
	 String Response =  given().log().all().header("Content-Type", "application/json").body(DynamicPayload.addbook(isbn, aisle)).
	  when().post("Library/Addbook.php").
	  then().log().all().assertThat().statusCode(200).extract().response().asString();
	 
	 
	 JsonPath js = new JsonPath(Response);
	 String id = js.getString("ID");
	 
	 System.out.println(id);	 

	 String DeleteResponse =  given().log().all().header("Content-Type", "application/json").body(DynamicPayload.deletebook(isbn, aisle)).
	  when().post("/Library/DeleteBook.php").
	  then().log().all().assertThat().statusCode(200).extract().response().asString();
	 
	 
	 JsonPath js1 = new JsonPath(DeleteResponse);
	 String Msg = js1.getString("msg");
	 
	 System.out.println(Msg);	
	  
  }
  
  @DataProvider(name="isbnaisledata")
  public Object[][] getData()
  {
	  return new Object[][] {{"a343434bc", "def"}, {"gh675675676i","jkl"}};
  }
}
