package com.swapnil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

	private String city;
	private String streetNo;
	private String state;
	private Integer bedRoom;
	private Integer hall;
	private Integer rent;
	private boolean availability=false;
}
