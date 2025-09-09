package BasicsPackage;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class complexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. Print No of courses returned by API - done
//
//		2.Print Purchase Amount - done
//
//		3. Print Title of the first course - done
//
//		4. Print All course titles and their respective Prices - done
//
//		5. Print no of copies sold by RPA Course - done
//
//		6. Verify if Sum of all Course prices matches with Purchase Amount - done
		
		JsonPath js = new JsonPath(PayloadTest.complexjson());
		int i=0;
		int Sum = 0;


		int count;
		count = js.getInt("courses.size()");
		System.out.println(count);

		
		int PurchaseAmount;
		PurchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(PurchaseAmount);

		String Title;
		Title = js.getString("courses[0].title");
		System.out.println(Title);

		for(i=0; i<count; i++)
		{
			String Course = js.getString("courses["+i+"].title");
			int Price = js.getInt("courses["+i+"].price");
			int Copies = js.getInt("courses["+i+"].copies");
			int Amount = Price * Copies;
			Sum = Sum+Amount;
				
			System.out.println(Course);
			System.out.println(Price);
			System.out.println(Copies);
			System.out.println("Total amount of " +Course+ "=" +Amount);

			
			if(Course.equalsIgnoreCase("RPA"))
			{
				Copies = js.getInt("courses.copies["+i+"]");
				System.out.println(Copies);			
			}

			
		}
		
		System.out.println("Grand total amount = " +Sum);

		
		Assert.assertEquals(PurchaseAmount, Sum);
		System.out.println("Purchase amount matches with the total sum");


	}
	
}



