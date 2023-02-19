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
	@OneToOne(cascade = CascadeType.ALL)
	private Tenat tenat;
	
}
