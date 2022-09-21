package com.openkart.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName="buildProduct")
@NoArgsConstructor
//Validation for Product 
public class ProductRequest {

	@Min(value=301, message="Product ID:Product ID Must Be in Between 300 to 400!!!")
	@Max(value=400, message="Product ID:Product ID  Must Be in Between 300 to 400!!!")
	private int productId;
	@Min(value=201, message="Seller Id: Seller ID Must Be in Between 200 to 300!!!")
	@Max(value=300, message="Seller Id: Seller ID Must Be in Between 200 to 300!!!")
	private int sellerId;
	@NotBlank(message="Hey Name should be Not Blank!!! ")
	private String productName;
	@NotBlank(message="Hey Type should be Not Blank!!! ")
	private String productType;
	@Min(value=1, message="price:Please Enter Correct Price!!!")
	private float  productPrice;
	@NotBlank(message="Hey Description should be Not Blank!!! ")
	private String productDesc;
	@Min(value=1, message="Quantity:Minimum 1 Quantity Is Required!!!")
	private int  productQuant;

}
