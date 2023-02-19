package com.swapnil.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tenatId;
	private String firstName;
	private String lastName;
	private String adharNo;
	@ManyToOne(cascade = CascadeType.ALL)
	private LandLord landlord;
}
