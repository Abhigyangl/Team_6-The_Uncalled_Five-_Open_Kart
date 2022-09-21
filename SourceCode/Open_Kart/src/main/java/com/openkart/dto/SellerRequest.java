package com.openkart.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="buildSeller")
@NoArgsConstructor
//Validation for Seller Controller
public class SellerRequest {

	@Min(value=201, message="Seller Id: Seller ID Must Be in Between 200 to 300!!!")
	@Max(value=300, message="Seller Id: Seller ID Must Be in Between 200 to 300!!!")
	private int sellerId;
	@NotBlank(message="Name cant be blank!!! ")
	private String sellerName;
	@Pattern(regexp="[6-9][]0-9]{9}",message="Moble number should be 10 digits and started with digit 6, 7 , 8 and 9")
	private String sellerPhone;
	@NotBlank
	@Email(message="Email should be in proper format!!!")
	private String sellerEmail;
	@NotBlank(message="Password cant be Blank!!!")
	private String sellerPassword;
	@NotBlank(message="Addtress cant be blank!!!")
	private String sellerAddress;
	
}
