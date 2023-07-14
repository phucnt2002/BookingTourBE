package com.vn.tour.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "location")
public class Location {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq",allocationSize = 1)
	private Long id;
	
	@Column(name = "location_name")
	private String locationName;
	
	@Column(name = "address")
	private String address;
	
	@Column (name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tour_id", referencedColumnName = "id")
	private Tour tour;
	
	
	public Location() {
		super();
	}


	public Location(Long id, String locationName, String address, String city, String country, Tour tour) {
		super();
		this.id = id;
		this.locationName = locationName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.tour = tour;
	}
	
	
	public Tour getTour() {
		return tour;
	}


	public void setTour(Tour tour) {
		this.tour = tour;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
