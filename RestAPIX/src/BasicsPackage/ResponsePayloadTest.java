package BasicsPackage;

public class ResponsePayloadTest {
	

	public static String GetPlace(String Place_ID, String NewAddress)
	{
		return "{\n"
				+ "\"place_id\":\""+Place_ID+"\",\n"
				+ "\"address\":\""+NewAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}\n"
				+ "";

	}


}

