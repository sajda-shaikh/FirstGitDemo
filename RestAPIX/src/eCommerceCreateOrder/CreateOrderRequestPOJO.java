package eCommerceCreateOrder;

import java.util.List;

public class CreateOrderRequestPOJO {
	
	private List<CreateOrderChildRequestPOJO> orders;

	
	public List<CreateOrderChildRequestPOJO> getOrders() {
		return orders;
	}

	public void setOrders(List<CreateOrderChildRequestPOJO> orders) {
		this.orders = orders;
	}



}
