package com.user.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.InvalidOrderException;
import com.user.entities.Inventory;
import com.user.entities.LoginCredentials;
import com.user.entities.OrderItems;
import com.user.entities.Orders;
import com.user.entities.Payment;
import com.user.entities.Supplier;
import com.user.entities.User;
import com.user.exception.InvalidRoleException;
import com.user.exception.InvalidUserIdException;
import com.user.feignclient.FeignToInventory;
import com.user.feignclient.FeignToOrderTable;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
@EnableMethodSecurity
public class UserController {
	
	@Autowired
	private FeignToOrderTable feign;
	
	@Autowired
	private FeignToInventory feignInv;
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/register")
	public User register(@RequestBody User user) throws InvalidRoleException
	{
		return service.add(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginCredentials credentials)
	{
		User user = userbyname(credentials.getUserName());
		return service.verify(user);
	}
	
	@GetMapping("/sample")
	public String sample()
	{
		return "<h1> URLS </h1><br>"
				+ "<h2>For User [ 'add', 'viewusers', 'viewbyrole/{role}', '{userid}', 'delete/{userid}' ]</h2><br>"
				+ "<h2>For Admin [ 'admin/order(GET)', admin/order/{orderid}(GET),'admin/order(POST)', 'admin/order/{orderid}(DELETE)']</h2><br>"
				+ "<h2>For Admin [ 'admin/orderitems(GET)', admin/orderitems/{itemid}(GET), 'admin/orderitems(POST)', 'admin/orderitems/{orderitemid}(DELETE)' ]</h2><br>"
				+ "<h2>For Admin [ 'admin/inventory(GET)', admin/inventory/{drugid}(GET),'admin/inventory(POST)', 'admin/inventory/{drugid}(DELETE)' ]</h2><br>"
				+ "<h2>For Admin [ 'admin/supplier(GET)', admin/supplier/{supplierid}(GET),'admin/supplier(POST)', 'admin/supplier/{supplierId}(DELETE)' ]</h2><br>"
				+ "<h2>For Doctor [ 'doctor/order(GET)','doctor/purchase/{id}(PURCHASE)'";
	}
	
	@PostMapping("/add")
	public User add(@RequestBody User user) throws InvalidRoleException
	{
		return service.add(user);
	}
	
	@GetMapping("/viewusers")
	public List<User> allUser()
	{
		return service.view();
	}
	
	@GetMapping("/viewuser/{username}")
	public User userbyname(@PathVariable String username)
	{
		return service.findByUserName(username);
	}
	
	@GetMapping("/{userid}")
	public User allUserbyid(@PathVariable int userid) throws InvalidUserIdException
	{
		return service.viewById(userid);
	}
	
	@GetMapping("/viewbyrole/{role}")
	public List<User> viewUserByRole(@PathVariable String role) throws InvalidRoleException
	{
		return service.viewByRole(role);
	}
	
	@DeleteMapping("/delete/{userid}")
	public String delete(@PathVariable int userid) throws InvalidUserIdException
	{
		return service.delete(userid);
	}
	
	
	//ADMIN PRIVILAGES
	
	//order table GET
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/order")
	public List<Orders> getorder()
	{
		return feign.getorders();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/order/{orderid}")
	public Orders getorderbyid(@PathVariable int orderid)
	{
		return feign.getordersbyid(orderid);
	}
	
	//order table POST
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/order")
	public Orders addorder(@RequestBody Orders order) throws InvalidOrderException
	{
		return feign.addorder(order);
	}
	
	//order table DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/order/{orderid}")
	public String deleteorder(@PathVariable int orderid)
	{
		return feign.deleteorder(orderid);
	}
	
	
	//order items table GET
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/orderitems")
	public List<OrderItems> getorderitems()
	{
		return feign.getOrderItems();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/orderitems/{itemid}")
	public OrderItems getorderitems(@PathVariable int itemid)
	{
		return feign.getOrderItemsbyid(itemid);
	}
	
	//order items table POST
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/orderitems")
	public OrderItems addorderitems(@RequestBody OrderItems item)
	{
		return feign.addorderitems(item);
	}
	
	//order items table DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/orderitems/{orderitemid}")
	public String deleteorderitems(@PathVariable int orderitemid)
	{
		return feign.deleteorder(orderitemid);
	}
	
	
	
	//inventory table GET
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/inventory")
	public List<Inventory> viewinventory()
	{
		return feignInv.getinventory();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/inventory/{drugid}")
	public Inventory viewinventorybyid(@PathVariable int drugid)
	{
		return feignInv.getinventorybyid(drugid);
	}
	
	//inventory table POST
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/inventory")
	public Inventory addinventory(@RequestBody Inventory inventory)
	{
		return feignInv.addinventory(inventory);
	}
	
	//inventory table DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/inventory/{drugid}")
	public String deleteinventory(@PathVariable int drugid)
	{
		return feignInv.deleteinventory(drugid);
	}
	
	
	//supplier table GET
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/supplier")
	public List<Supplier> viewsup()
	{
		return feignInv.getsupplier();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/supplier/{supplierid}")
	public Supplier viewsup(@PathVariable int supplierid)
	{
		return feignInv.getsupplierbyid(supplierid);
	}
	
	//supplier table POST
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/supplier")
	public Supplier addsup(@RequestBody Supplier supp)
	{
		return feignInv.addsupplier(supp);
	}
	
	//supplier table DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/supplier/{supplierId}")
	public String deletesup(@PathVariable int supplierId)
	{
		return feignInv.deletesupplier(supplierId);
	}
	
	
	
	//DOCTOR PRIVILAGES
	
	//orderitem table GET
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping("/doctor/orderitems")
	public List<OrderItems> getdoctororder()
	{
		return feign.getOrderItems();
	}
	
	//order table purchasing
	@PreAuthorize("hasRole('DOCTOR')")
	@GetMapping("/doctor/purchase/{userid}/{orderid}")
	public Orders buy(@PathVariable int userid, @PathVariable int orderid) throws InvalidUserIdException
	{
		User user = allUserbyid(userid);
		return feign.updateOrder(user ,orderid,"PLACED");
	}
	
//	//payment table mapping
//	@GetMapping("/doctor/payment/{userId}")
//	public List<Payment> payment(@PathVariable int userid)
//	{
//		return feign.getPaymentBuUserId(userid);
//	}
	
	
}
