package com.vn.tour.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.vn.tour.entity.*;
import com.vn.tour.repository.*;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TourGuideRepository tourGuideRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    LocationRepository locationRepository;
    @Override
    public ResponseObject login(String userName, String password) {
        // TODO Auto-generated method stub
        Optional<Account> acc = accountRepository.findByUserName(userName);
        if (acc.isPresent()) {
            return acc.get().getPassword().equals(password) ?
                    new ResponseObject("ok", "Login successful", acc) :
                    new ResponseObject("failed", "Password is wrong", null);
        } else {
            return new ResponseObject("failed", "User name is not correct", "");
        }
    }

    @Override
    public ResponseObject blockAccount(Long id) {
        // TODO Auto-generated method stub
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            if (accountOptional.get().getStatus().equalsIgnoreCase("active")) {
                Account account = accountOptional.get();
                account.setStatus("block");
                accountRepository.save(account);
                return new ResponseObject("ok", "Block account successfully", account);
            } else {
                return new ResponseObject("failed", "Account already blocked or not found", null);
            }
        } else {
            return new ResponseObject("failed", "Cannot find account with id = " + id, null);
        }

    }

    @Override
    public ResponseObject unBlockAccount(Long id) {
        // TODO Auto-generated method stub
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {

            if (account.isPresent() && account.get().getStatus().equalsIgnoreCase("block")) {
                account.get().setStatus("active");
                accountRepository.save(account.get());
                return new ResponseObject("ok", "Active account successfully", account);
            } else {
                return new ResponseObject("failed", "Account already active", account);
            }
        } else {
            return new ResponseObject("failed", "Cannot find account with id = " + id, null);
        }
    }

    @Override
    public String createAccount(String userName, String password, String status, String role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseObject createCustomer(String firstName, String lastName,
                                 String email, String phoneNumber, String address, Account account) {
        // TODO Auto-generated method stub
        Customer customer = new Customer(null, firstName, lastName, email, phoneNumber, address, account, null);
        if (accountRepository.findByUserName(account.getUserName()).isEmpty()) {
            try {
                //  Block of code to try
                customerRepository.save(customer);
                return new ResponseObject("ok", "Create Account successfully", customer);
            }
            catch(Exception e) {
                return new ResponseObject("failed", e.toString(), customer);
            }
        } else {
            return new ResponseObject("failed", "Account is already", customer);
        }
    }

    @Override
    public ResponseObject createTourGuide(String guideName, String guideBio, Account account) {
        // TODO Auto-generated method stub
        TourGuide tourGuide = new TourGuide(null, guideName, guideBio, account);
        if (accountRepository.findByUserName(account.getUserName()).isEmpty()) {
            try {
                //  Block of code to try
                tourGuideRepository.save(tourGuide);
                return new ResponseObject("ok", "Create Account successfully", tourGuide);
            }
            catch(Exception e) {
                return new ResponseObject("failed", e.toString(), tourGuide);
            }
        } else {
            return new ResponseObject("failed", "Account is already", tourGuide);
        }
    }

    @Override
    public ResponseObject createTour(String tourName, String description, Long price, Long duration,Long quantity, Date timeStart, Date timeEnd, List<Location> location) {
        Tour tour = new Tour(null, tourName, description, price, duration, quantity, timeStart, timeEnd, null, null, location);
        try{
            tourRepository.save(tour);
            return new ResponseObject("ok", "Create a tour successfully", tour);
        }catch (Exception e){
            return new ResponseObject("failed", "Cannot create a tour" + e.toString(), tour);
        }
    }

    @Override
    public ResponseObject createLocation(String locationName, String address, String city, String country) {
        // TODO Auto-generated method stub
        if(locationRepository.findByLocationName(locationName).isEmpty()){
            Location location = new Location(null, locationName, address, city, country, null);
            try{
                locationRepository.save(location);
                return new ResponseObject("ok", "Create a location successfully", location);
            }catch (Exception e){
                return new ResponseObject("failed", "Cannot create a location", location);
            }
        }else{
            return new ResponseObject("failed", "locationName is already", "");
        }
    }

    @Override
    public ResponseObject getAllTour() {
        List<Tour> tours = tourRepository.findAll();
        if(tours.isEmpty()){
            return new ResponseObject("failed", "Cannot find ant record tour", "");
        }else{
            return new ResponseObject("ok", "Get tours successfully", tours);
        }
    }

    @Override
    public ResponseObject getAllCustomer() {

        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()){
            return new ResponseObject("failed", "Cannot find any record Customer", "");
        }else{
            return new ResponseObject("ok", "Get all Customer successfully", customers);
        }
    }

    @Override
    public ResponseObject getAllTourGuide() {
        List<TourGuide> tourGuide = tourGuideRepository.findAll();
        if(tourGuide.isEmpty()){
            return new ResponseObject("failed", "Cannot find any record tourGuide", "");
        }else{
            return new ResponseObject("ok", "Get all tourGuide successfully", tourGuide);
        }
    }

    @Override
    public ResponseObject getAllLocation() {
        List<Location> locations = locationRepository.findAll();
        if(locations.isEmpty()){
            return new ResponseObject("failed", "Cannot find ant record locations", "");
        }else{
            return new ResponseObject("ok", "Get locations successfully", locations);
        }
    }

    @Override
    public ResponseObject updateTour(Tour tour) {
        return null;
    }

    @Override
    public ResponseObject updateLocation(Location location) {
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        // TODO Auto-generated method stub
        return accountRepository.findAll();
    }
}
