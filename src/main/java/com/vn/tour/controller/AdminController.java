package com.vn.tour.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vn.tour.entity.Account;
import com.vn.tour.entity.Customer;
import com.vn.tour.entity.ResponseObject;
import com.vn.tour.service.IAdminService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bookingtour/admin")
public class AdminController {
	@Autowired
	IAdminService imAdminService;
	@GetMapping("login")
	public ResponseEntity<ResponseObject> login(@RequestBody Account account){
		return ResponseEntity.status(HttpStatus.OK).body(imAdminService.login(account.getUserName(), account.getPassword()));
	}
	@PostMapping("/createCustomer")
	public ResponseEntity<ResponseObject> createCustomer(@RequestBody Customer customer) {
		ResponseObject responseObject = imAdminService.createCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(), customer.getAccount());
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}
	
	@GetMapping("/getAccount")
	public ResponseEntity<ResponseObject> getAccounts() {
		List<Account> data = imAdminService.getAllAccount();
		return data.isEmpty()
				? ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Canot find any account record", ""))
				: ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Get all account successfully", data));
	}

	@PutMapping("/blockAccount/{id}")
	public ResponseEntity<ResponseObject> blockAccount(@PathVariable("id") Long id){
		ResponseObject responseObject = imAdminService.blockAccout(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@PutMapping("/unBlockAccount/{id}")
	public ResponseEntity<ResponseObject> unBlockAccount(@PathVariable("id") Long id){
		ResponseObject responseObject = imAdminService.unBlockAccout(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}
}
