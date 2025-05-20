package com.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.user.entities.OrderItems;
import com.user.entities.Orders;
import com.user.entities.Payment;
import com.user.entities.User;

//@FeignClient(url="http://localhost:8081", value="OrderItems")
@FeignClient(name="ORDERS-PHARMACY")
public interface FeignToOrderTable {
	
	//ORDER TABLE
	@GetMapping("/orders/view")
	public List<Orders> getorders();
	
	@GetMapping("/orders/{orderid}")
	public Orders getordersbyid(@PathVariable int orderid);
	
//	@GetMapping("/payment/{userid}")
//	public List<Payment> getPaymentBuUserId(@PathVariable int userid);
	
	@PostMapping("/orders/add")
	public Orders addorder(@RequestBody Orders order);
	
	@DeleteMapping("orders/delete/{orderid}")
	public String deleteorder(int orderid);
	
	@PutMapping("/orders/{orderId}/{status}")
	Orders updateOrder(@RequestBody User user, @PathVariable int orderId, @PathVariable String status);
	

	//ORDER ITEMS TABLE
	@GetMapping("/orderitems/view")
	public List<OrderItems> getOrderItems();
	
	@GetMapping("/orderitems/{itemid}")
	public OrderItems getOrderItemsbyid(@PathVariable int itemid);
	
	@PostMapping("/orderitems/add")
	public OrderItems addorderitems(@RequestBody OrderItems item);
	
	@DeleteMapping("orderitems/delete/{orderitemid}")
	public String deleteorderitem(@PathVariable int orderitemid);
	
}
