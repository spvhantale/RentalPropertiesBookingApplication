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
public class Tenant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tenatId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String adharNo;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Property property;
	
	public Tenant(String firstName, String lastName, String mobileNo, String adharNo, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.adharNo = adharNo;
		this.password = password;
	}
	
	
	
	
}
