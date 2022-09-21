package com.openkart.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor(staticName="build")
@NoArgsConstructor
// Validation for Buyer Controller
public class BuyerRequest {
	@Min(value=100, message="Buyer ID:Buyer ID Must Be More Then 100!!!")
	@Max(value=200, message="Buyer ID:Buyer ID Must Be Less Then 200!!!")
	private int buyerId;
	@NotBlank(message="Name cant be Blank!!! ")
	private String buyerName;
	@Pattern(regexp="[6-9][]0-9]{9}",message="Moble number should be 10 digits and start with digit 6,7,8 and 9")
	private String buyerPhone;
	@NotBlank
	@Email(message=" Email should be in proper format!!!")
	private String buyerEmail;
	@NotBlank(message="Password cant be Blank!!!")
	private String buyerPassword;
	@NotBlank(message="Address cant be blank!!!")
	private String buyerAddress;
}
