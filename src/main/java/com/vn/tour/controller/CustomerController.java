package com.vn.tour.controller;

import com.vn.tour.entity.Account;
import com.vn.tour.entity.Booking;
import com.vn.tour.entity.Customer;
import com.vn.tour.entity.ResponseObject;
import com.vn.tour.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bookingtour/customer")
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;
    @GetMapping("/getBookingByCustomerId/{id}")
    public ResponseEntity<ResponseObject> getBookingByCustomerId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerService.getBookingByCustomerId(id));
    }
    @PutMapping("/updatePassword")
    public ResponseEntity<ResponseObject> updatePassword(@RequestBody Account account){
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerService.updatePassword(account));
    }

    @PostMapping("/createBooking")
    public ResponseEntity<ResponseObject> createBooking(@RequestBody Booking booking){
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerService.createBooking(booking));
    }
    @PutMapping("/updateBooking")
    public ResponseEntity<ResponseObject> updateBooking(@RequestBody Booking booking){
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerService.updateBooking(booking));
    }

}
