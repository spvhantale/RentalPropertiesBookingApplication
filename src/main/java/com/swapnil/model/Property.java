package com.swapnil.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	private String city;
	private String streetNo;
	private String state;
	private Integer bedRoom;
	private Integer hall;
	private Integer rent;
	private boolean availability=true;
	@OneToOne(cascade = CascadeType.ALL)
	private Tenant tenant;
	public Property(String city, String streetNo, String state, Integer bedRoom, Integer hall, Integer rent,
			boolean availability) {
		super();
		this.city = city;
		this.streetNo = streetNo;
		this.state = state;
		this.bedRoom = bedRoom;
		this.hall = hall;
		this.rent = rent;
		this.availability = availability;
	}
	
	
	
}
