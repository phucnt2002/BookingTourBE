package com.vn.tour.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tour_guide")
public class TourGuide {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_guide_seq")
    @SequenceGenerator(name = "tour_guide_seq", sequenceName = "tour_guide_seq",allocationSize = 1)
	private Long id;
	
	@Column(name = "guide_name")
	private String guideName;
	
	@Column(name = "guide_bio")
	private String guideBio;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	public TourGuide() {
		super();
	}
	public TourGuide(Long id, String guideName, String guideBio, Account account) {
		super();
		this.id = id;
		this.guideName = guideName;
		this.guideBio = guideBio;
		this.account = account;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getGuideBio() {
		return guideBio;
	}
	public void setGuideBio(String guideBio) {
		this.guideBio = guideBio;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
