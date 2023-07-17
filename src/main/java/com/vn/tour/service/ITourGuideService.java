package com.vn.tour.service;

import com.vn.tour.entity.ResponseObject;
import org.springframework.stereotype.Service;

public interface ITourGuideService {
    public ResponseObject bookLeadTour(Long tourId, Long tourGuideId);

}
