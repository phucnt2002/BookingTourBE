package com.vn.tour.repository;

import com.vn.tour.entity.Location;
import com.vn.tour.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    public Optional<Location> findByLocationName(String locationName);
}
