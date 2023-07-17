package com.vn.tour.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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

	@JsonIgnore
	@ManyToMany(mappedBy = "locations")
	private List<Tour> tours;
	
	
	public Location() {
		super();
	}


	public Location(Long id, String locationName, String address, String city, String country, List<Tour> tours) {
		this.id = id;
		this.locationName = locationName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.tours = tours;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
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
