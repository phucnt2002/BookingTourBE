package com.vn.tour.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.vn.tour.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface IAdminService {
	public ResponseObject login(String userName, String password);
	public ResponseObject blockAccount(Long id);
	public ResponseObject unBlockAccount(Long id);
	public List<Account> getAllAccount();
	public String createAccount(String userName, String password, String status, String role);
	public ResponseObject createCustomer(String firstName, String lastName, String email, String phoneNumber, String address, Account account);
	public ResponseObject createTourGuide(String guideName, String guideBio, Account account);
	public ResponseObject createTour(String tourName, String description, Long price, Long duration,Long quantity, Date timeStart, Date timeEnd, String imgURL, List<Location> location);
	public ResponseObject createLocation(String locationName, String address, String city, String country);
	public ResponseObject getAllTour();
	public ResponseObject getAllCustomer();
	public ResponseObject getAllTourGuide();
	public ResponseObject getAllLocation();
	public ResponseObject updateTour(Tour tour);
	public ResponseObject updateLocation(Location location);
	String uploadFile(MultipartFile multipartFile) throws IOException;

}
