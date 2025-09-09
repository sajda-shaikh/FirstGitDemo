package LibraryPackage;

public class DynamicPayload {
	
	public static String addbook(String isb, String aisl)
	{
		
		String AddBookPayload = "{\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isb+"\",\n"
				+ "\"aisle\":\""+aisl+"\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}\n"
				+ "";
		
		return AddBookPayload;

	}
	
	public static String deletebook(String isbn, String aisle)
	
	{
		String id = isbn+aisle;
		String s = "{\n"
		+ " \n"
		+ "\"ID\" : \""+id+"\"\n"
		+ " \n"
		+ "} \n"
		+ "";
		return s;
	}


}
