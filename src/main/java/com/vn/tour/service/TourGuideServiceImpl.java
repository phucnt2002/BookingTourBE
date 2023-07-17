package com.vn.tour.service;

import com.vn.tour.entity.ResponseObject;
import com.vn.tour.entity.Tour;
import com.vn.tour.entity.TourGuide;
import com.vn.tour.repository.TourGuideRepository;
import com.vn.tour.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class TourGuideServiceImpl implements ITourGuideService {
    @Autowired
    TourGuideRepository tourGuideRepository;
    @Autowired
    TourRepository tourRepository;
    @Override
    public ResponseObject bookLeadTour(Long tourId, Long tourGuideId) {
        Optional<TourGuide> tourGuide = tourGuideRepository.findById(tourGuideId);
        Optional<Tour> tour = tourRepository.findById(tourId);
        if(tour.isEmpty()){
            return new ResponseObject("failed", "Can't find tour with id = "+tourId, "");
        }else{
            tour.get().setTourGuide(tourGuide.get());
            tourRepository.save(tour.get());
            return new ResponseObject("ok", "Set lead tour for tour guide successfully", tour);
        }
    }
}
