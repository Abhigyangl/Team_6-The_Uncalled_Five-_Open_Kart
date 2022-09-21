package com.openkart.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="buildCart")
@NoArgsConstructor
//Validation for Cart
public class CartRequest {

	@Min(value=100, message="Buyer ID:Buyer ID Must Be in Between 100 to 200!!!")
	@Max(value=200, message="Buyer ID:Buyer ID Must Be in Between 100 to 200!!!")
	private int buyerId;
	@Min(value=301, message="Product ID:Product ID  Be in Between 300 to 400!!!")
	@Max(value=400, message="Product ID:Product ID  Be in Between 300 to 400!!!")
	private int proId;
	@Min(value=1, message="Product Quantity:Product Quantity Must Be More Then Zero!!!")
	private int proQuant;
}
