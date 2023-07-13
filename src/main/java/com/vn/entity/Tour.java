package com.vn.entity;

import java.sql.Date;

import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Department;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tour")
public class Tour {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_seq",allocationSize = 1)
	private Long id;
	@Column(name = "tour_name")
	private String tourName;

	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "duration")
	private Long duration;
	
	@Column(name = "time_start")
	private Date timeStart;
	
	@Column(name = "time_end")
	private Date timeEnd;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking booking;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guide_id", referencedColumnName = "id")
	private TourGuide tourGuide;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	public Tour(Long id, String tourName, String description, Long price, Long duration, Date timeStart, Date timeEnd,
			Booking booking, TourGuide tourGuide, Location location) {
		super();
		this.id = id;
		this.tourName = tourName;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.booking = booking;
		this.tourGuide = tourGuide;
		this.location = location;
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

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public TourGuide getTourGuide() {
		return tourGuide;
	}

	public void setTourGuide(TourGuide tourGuide) {
		this.tourGuide = tourGuide;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
