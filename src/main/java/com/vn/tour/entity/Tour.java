package com.vn.tour.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_seq", allocationSize = 1)
    private Long id;
    @Column(name = "tour_name")
    private String tourName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "quality")
    private  Long quality;

    @Column(name = "time_start")
    private Date timeStart;

    @Column(name = "time_end")
    private Date timeEnd;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Booking> booking;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guide_id", referencedColumnName = "id")
    private TourGuide tourGuide;

    //	@JsonManagedReference
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)//chú ý chỗ này khi dùng quan hệ many-many phải dùng MERGE
    @JoinTable(
            name = "tour_location",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;


    public List<Location> getLocation() {
        return locations;
    }

    public void setLocation(List<Location> location) {
        this.locations = location;
    }

    public Tour() {
        super();
    }

    public Tour(Long id, String tourName, String description, Long price, Long duration, Long quantity, Date timeStart, Date timeEnd, List<Booking> booking, TourGuide tourGuide, List<Location> locations) {
        this.id = id;
        this.tourName = tourName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.quality = quantity;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.booking = booking;
        this.tourGuide = tourGuide;
        this.locations = locations;
    }

    public Long getQuality() {
        return quality;
    }

    public void setQuality(Long quality) {
        this.quality = quality;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    public TourGuide getTourGuide() {
        return tourGuide;
    }

    public void setTourGuide(TourGuide tourGuide) {
        this.tourGuide = tourGuide;
    }
}
