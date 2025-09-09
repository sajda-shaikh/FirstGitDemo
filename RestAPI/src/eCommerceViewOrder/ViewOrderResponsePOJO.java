package eCommerceViewOrder;

public class ViewOrderResponsePOJO {

	private ViewOrderChildPOJO data;
	public ViewOrderChildPOJO getData() {
		return data;
	}
	public void setData(ViewOrderChildPOJO data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
	
	
}
