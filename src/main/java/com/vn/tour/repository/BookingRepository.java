package com.vn.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tour.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
