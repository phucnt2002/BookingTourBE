package com.vn.tour.controller;

import com.vn.tour.entity.ResponseObject;
import com.vn.tour.service.ITourGuideService;
import org.hibernate.type.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bookingtour/tourGuide")
public class TourGuideController {
    @Autowired
    ITourGuideService iTourGuideService;
    @PutMapping("/bookLeadTour")
    public ResponseEntity<ResponseObject> bookLeadTour(@RequestParam Long tourId, @RequestParam Long tourGuideId){
        return ResponseEntity.status(HttpStatus.OK).body(iTourGuideService.bookLeadTour(tourId, tourGuideId));
    }
}
