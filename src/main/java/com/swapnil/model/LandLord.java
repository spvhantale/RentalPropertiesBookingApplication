package com.swapnil.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandLord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer landLordId;
	private String firstName;
	private String lastName;
	private String adharNo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Tenat> tenats=new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Property> properties=new ArrayList<>();
}
