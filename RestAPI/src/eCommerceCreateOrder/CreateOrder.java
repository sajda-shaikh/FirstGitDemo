package eCommerceCreateOrder;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import eCommerceLogin.LoginRequestPOJO;
import eCommerceLogin.LoginResponsePOJO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CreateOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//login to the Website

		LoginRequestPOJO LoginReq = new LoginRequestPOJO();		
		LoginReq.setUserEmail("sajda@gmail.com");
		LoginReq.setUserPassword("Sda123!@");
		
		RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com").build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();
			
		LoginResponsePOJO response = given().spec(req).body(LoginReq).log().all()
				.when().post("/api/ecom/auth/login")
				.then().spec(res).log().all().extract().as(LoginResponsePOJO.class);
		
		String Logintoken;
		String UserID;
		
		Logintoken = response.getToken();
		UserID = response.getUserId();
		
		System.out.println("Access Token = " +Logintoken);
		System.out.println("User ID = " +UserID);
		
		//Add a new Product to the Website

		RequestSpecification reqAddProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", Logintoken).build();
		ResponseSpecification resAddProduct = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
		
		String responseAddProduct = given().spec(reqAddProduct)
				.param("productName", "Shoes").param("productAddedBy", UserID)
				.param("productCategory", "fashion").param("productSubCategory", "Shoes")
				.param("productPrice", "21500").param("productDescription", "Addias Originals")
				.param("productFor", "women")
				.multiPart("productImage", new File("/Users/sajda/Desktop/Screenshot 2025-08-10 at 19.14.46.png")).log().all()
				.when().post("/api/ecom/product/add-product")
				.then().spec(resAddProduct).log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(responseAddProduct);
		
		String ProductID = js.getString("productId");
		
		System.out.println("Product ID = "+ProductID);
		
		//Create Order
		
		CreateOrderChildRequestPOJO COChild = new CreateOrderChildRequestPOJO();
		COChild.setCountry("United Kingdom");
		COChild.setProductOrderedId(ProductID);	
		
		List<CreateOrderChildRequestPOJO> orderDetailsList = new ArrayList<CreateOrderChildRequestPOJO>();
		orderDetailsList.add(COChild);
		
		CreateOrderRequestPOJO CO = new CreateOrderRequestPOJO();
		CO.setOrders(orderDetailsList);
			
		RequestSpecification reqCreateOrder = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Logintoken).build();
		
		ResponseSpecification resCreateOrder = new ResponseSpecBuilder().expectStatusCode(201)
				.expectContentType(ContentType.JSON).build();
		
		CreateOrderResponsePOJO responseCreateOrder = given().spec(reqCreateOrder).body(CO).log().all()
				.when().post("/api/ecom/order/create-order")
				.then().spec(resCreateOrder).log().all().extract().as(CreateOrderResponsePOJO.class);
		
		List<String> OrderID = responseCreateOrder.getOrders();
		System.out.println("Order ID = "+OrderID);
	}

}
