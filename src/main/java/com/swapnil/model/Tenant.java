package com.swapnil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Size(min = 3, max = 15, message = "First name should be in 3 to 15 character")
	private String firstName;
	@Size(min = 3, max = 15, message = "Last name should be in 3 to 15 character")
	private String lastName;
	@Column(unique = true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Mobile number should be 10 digits with starting with 6 OR 7 OR 8 OR 9")
	private String mobileNumber;
	@Column(unique = true)
	@Size(min = 12, max = 12, message = "Adhar number should be 12 digits ")
	private String adharNumber;
	@Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])).{6,100}$", message = "Password is wrong ")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Property property;
	private String role;

	public Tenant(@Size(min = 3, max = 15, message = "First name should be in 3 to 15 character") String firstName,
			@Size(min = 3, max = 15, message = "Last name should be in 3 to 15 character") String lastName,
			@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Mobile number should be 10 digits with starting with 6 OR 7 OR 8 OR 9") String mobileNumber,
			@Size(min = 12, max = 12, message = "Adhar number should be 12 digits ") String adharNumber,
			@Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])).{6,100}$", message = "Password is wrong ") String password,
			String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.adharNumber = adharNumber;
		this.password = password;
		this.role = role;
	}

}
