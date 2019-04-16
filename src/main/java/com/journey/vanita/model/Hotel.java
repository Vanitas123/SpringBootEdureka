package com.journey.vanita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotNull
	@NotBlank
	@Column(name="name")
	private String hotelName;
	
	@Column(name="address")
	private String Address;

	@JsonIgnore
	@Column(name="description")
	private String description;

	@Column(name="totalrooms")
	@JsonIgnore
	private int totalRooms = 0;
	
	@Column(name="booked")
	@JsonIgnore
	private int booked = 0;

	public int getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}

	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
