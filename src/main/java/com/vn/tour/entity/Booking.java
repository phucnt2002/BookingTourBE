package com.vn.tour.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq",allocationSize = 1)
	private Long id;
	
	@Column(name = "booking_date")
	private Date bookingDate;
	
	@Column(name= "note")
	private String note;
	
//	@JsonManagedReference
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tour_id", referencedColumnName = "id")
	private Tour tour;
	@JsonIgnore
	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;


	public Booking() {
		super();
	}

	public Booking(Long id, Date bookingDate, String note, Tour tour, Customer customer) {
		this.id = id;
		this.bookingDate = bookingDate;
		this.note = note;
		this.tour = tour;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public String getNote() {
		return note;
	}

	public Tour getTour() {
		return tour;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
