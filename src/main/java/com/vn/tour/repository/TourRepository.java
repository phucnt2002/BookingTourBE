package com.vn.tour.repository;

import com.vn.tour.entity.Tour;
import com.vn.tour.entity.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

}