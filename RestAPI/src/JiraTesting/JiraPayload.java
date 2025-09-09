package JiraTesting;

public class JiraPayload {

	public static String JiraCreateIssuePayload() 
	{
		// TODO Auto-generated method stub

		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RSA\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Issue created using REST Assured.\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "";
		
	}

}
