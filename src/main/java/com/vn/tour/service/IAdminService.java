package com.vn.tour.service;

import java.sql.Date;
import java.util.List;

import com.vn.tour.entity.Account;
import com.vn.tour.entity.ResponseObject;
import org.springframework.http.ResponseEntity;


public interface IAdminService {
	public ResponseObject login(String userName, String password);
	public ResponseObject blockAccout(Long id);
	public ResponseObject unBlockAccout(Long id);
	public List<Account> getAllAccount();
	public String createAccount(String userName, String password, String status, String role);
	public ResponseObject createCustomer(String firstName, String lastName, String email, String phoneNumber, String address, Account account);
	public String createTourGuide(String userName, String password, String status, String guideName, String guideBio);
	public String createTour(String tourName, String description, Long price, Long duration, Date timeStart, Date timeEnd, Long location_id);
	public String createLocation(String locationName, String address, String city, String country);
}
