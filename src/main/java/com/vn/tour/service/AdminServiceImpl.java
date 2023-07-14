package com.vn.tour.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.vn.tour.entity.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vn.tour.entity.Account;
import com.vn.tour.entity.Customer;
import com.vn.tour.repository.AccountRepository;
import com.vn.tour.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

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
    public ResponseObject blockAccout(Long id) {
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
    public ResponseObject unBlockAccout(Long id) {
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
    public String createTourGuide(String userName, String password, String status, String guideName, String guideBio) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String createTour(String tourName, String description, Long price, Long duration, Date timeStart,
                             Date timeEnd, Long location_id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String createLocation(String locationName, String address, String city, String country) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        // TODO Auto-generated method stub
        return accountRepository.findAll();
    }

}
