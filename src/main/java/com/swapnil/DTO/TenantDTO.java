package com.swapnil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantDTO {

	private String firstName;
	private String lastName;
	private String adharNumber;
	private String mobileNumber;
	private String password;
	private String role;
	

}
