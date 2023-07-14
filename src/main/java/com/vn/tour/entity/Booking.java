package com.vn.tour.entity;

import java.sql.Date;
import java.util.List;

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
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<Tour> tour;
	
	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;


	public Booking() {
		super();
	}

	public Booking(Long id, Date bookingDate, String note, List<Tour> tour, Customer customer) {
		super();
		this.id = id;
		this.bookingDate = bookingDate;
		this.note = note;
		this.tour = tour;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Tour> getTour() {
		return tour;
	}

	public void setTour(List<Tour> tour) {
		this.tour = tour;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
