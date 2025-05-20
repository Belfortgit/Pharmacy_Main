package com.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.entities.Inventory;
import com.user.entities.Supplier;

@FeignClient(name="INVENTORY-SUPPLIER-PHARMACY")
public interface FeignToInventory {

	//INVENTORY TABLE
	@GetMapping("inventory/view")
	public List<Inventory> getinventory();
	
	@GetMapping("inventory/{drugid}")
	public Inventory getinventorybyid(@PathVariable int drugid);
	
	@PostMapping("/inventory/add")
	public Inventory addinventory(@RequestBody Inventory inventory);
	
	@DeleteMapping("/inventory/delete/{drugid}")
	public String deleteinventory(@PathVariable int drugid);
	
	
	//SUPPLIER TABLE
	@GetMapping("/supplier/view")
	public List<Supplier> getsupplier();
	
	@GetMapping("/supplier/{supplierid}")
	public Supplier getsupplierbyid(@PathVariable int supplierid);
	
	@PostMapping("/supplier/add")
	public Supplier addsupplier(@RequestBody Supplier supplier);
	
	@DeleteMapping("/supplier/delete/{supplierId}")
	public String deletesupplier(@PathVariable int supplierId);
}
