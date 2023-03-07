package com.swapnil.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propertyId;
	@Size(min = 3, max = 15, message = "City name should be greater than 3 and less than 15")
	private String city;
	@Size(min = 3, max = 5, message = "Street number should be greater than 3 and less than 5")
	private String streetNumber;
	@Size(min = 3, max = 15, message = "State should be greater than 3 and less than 15")
	private String state;
	private Integer bedRoom;
	private Integer hall;
	private Integer rent;
	private boolean availability = true;
	@OneToOne(cascade = CascadeType.ALL)
	private Tenant tenant;
	@ManyToOne(cascade = CascadeType.ALL)
	private LandLord landlord;

	public Property(
			@Size(min = 3, max = 15, message = "City name should be greater than 3 and less than 15") String city,
			@Size(min = 3, max = 5, message = "Street number should be greater than 3 and less than 5") String streetNumber,
			@Size(min = 3, max = 15, message = "State should be greater than 3 and less than 15") String state,
			Integer bedRoom, Integer hall, Integer rent, boolean availability, LandLord landlord) {
		super();
		this.city = city;
		this.streetNumber = streetNumber;
		this.state = state;
		this.bedRoom = bedRoom;
		this.hall = hall;
		this.rent = rent;
		this.availability = availability;
		this.landlord = landlord;
	}

}
