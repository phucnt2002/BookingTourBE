package com.vn.tour.service;

import com.vn.tour.entity.Account;
import com.vn.tour.entity.Booking;
import com.vn.tour.entity.Customer;
import com.vn.tour.entity.ResponseObject;

public interface ICustomerService {
    public ResponseObject getBookingByCustomerId(Long id);

    public ResponseObject updatePassword(Account account);
    public ResponseObject createBooking(Booking booking);
    public ResponseObject updateBooking(Booking booking);
    public ResponseObject cancelBooking(Long id);

}
