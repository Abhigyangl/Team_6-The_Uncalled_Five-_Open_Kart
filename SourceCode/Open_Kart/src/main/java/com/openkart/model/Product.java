package com.openkart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openkart.dto.SellerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="buildProduct")
@NoArgsConstructor
@Entity
@Table(name="Product")
public class Product 
{
	
	@Id
	@Column(name="Product_Id")
	private int productId;
	
	@Column(name="Seller_Id")
	private int sellerId;
	
	@Column(name="Product_Name")
	private String productName;
	
	@Column(name="Product_Type")
	private String productType;
	
	@Column(name="Product_Price")
	private float  productPrice;
	
	@Column(name="Product_Desc")
	private String productDesc;
	
	@Column(name="Product_Quant")
	private int  productQuant;
	
	

}
