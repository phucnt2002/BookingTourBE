package com.vn.tour.service;

import com.vn.tour.entity.*;
import com.vn.tour.repository.AccountRepository;
import com.vn.tour.repository.BookingRepository;
import com.vn.tour.repository.CustomerRepository;
import com.vn.tour.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSeviceImpl implements ICustomerService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TourRepository tourRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public ResponseObject getBookingByCustomerId(Long id) {
        List<Booking> booking = bookingRepository.findByCustomerId(id);
        if(booking == null){
            return new ResponseObject("failed", "Customer didn't have any booking", "");
        }else{
            return new ResponseObject("ok", "Find Booking by Id successfully", booking);
        }
    }

    @Override
    public ResponseObject updatePassword(Account account) {
        Optional<Account> getAccount = accountRepository.findById(account.getId());
        if(getAccount.isEmpty()){
            return new ResponseObject("failed", "Cannot find account", account);
        }else{
            getAccount.get().setPassword(account.getPassword());
            accountRepository.save(getAccount.get());
            return new ResponseObject("ok", "update password successfully", getAccount.get());
        }

    }

    @Override
    public ResponseObject createBooking(Booking booking, Long tourId, Long customerId) {
        Optional<Tour> tour = tourRepository.findById(tourId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(tour.isEmpty()||customer.isEmpty()){
            return new ResponseObject("failed", "Can't find tour or customer", "");
        }else{
            if(tour.get().getQuality()>0){
                try{
                    booking.setTour(tour.get());
                    booking.setCustomer(customer.get());
                    bookingRepository.save(booking);
                    tour.get().setQuality(tour.get().getQuality()-1);
                    Tour newTour = tour.get();
                    tourRepository.save(newTour);
                    System.out.println(booking.getTour().getId());
                    return new ResponseObject("ok", "Create booking successfully", booking);
                }catch (Exception e){
                    return new ResponseObject("failed", "Can't create booking successfully"+e, booking);
                }
            }else{
                return new ResponseObject("failed", "Tour was sold out", tour);
            }

        }

    }

    @Override
    public ResponseObject updateBooking(Booking booking) {
        Optional<Booking> getBooking = bookingRepository.findById(booking.getId());
        if(getBooking.isEmpty()){
            return new ResponseObject("failed", "Can't find booking record with id ="+getBooking.get().getId(), "");
        }else{
            bookingRepository.save(getBooking.get());
            return new ResponseObject("ok", "Update successfully", getBooking);
        }
    }

    @Override
    public ResponseObject cancelBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isEmpty()){
            return new ResponseObject("failed", "Cannot find booking record with id ="+id, "");
        }else{
            bookingRepository.delete(booking.get());
            return new ResponseObject("ok", "Delete booking successfully with id = "+id, booking);
        }
    }
}
