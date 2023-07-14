package com.vn.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tour.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
