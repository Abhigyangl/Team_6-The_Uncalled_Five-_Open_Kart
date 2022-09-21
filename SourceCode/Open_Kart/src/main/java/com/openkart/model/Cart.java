package com.openkart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openkart.dto.CartRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="buildCart")
@NoArgsConstructor
@Entity
@Table(name="Cart")
public class Cart 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name="Cart_Id")
	private int cartId;
	
	@Column(name="Buyer_Id")
	private int buyerId;
	
	@Column(name="Pro_Id")
	private int proId;
	
	@Column(name="Pro_Name")
	private String proName;
	
	@Column(name="Pro_Price")
	private float proPrice;
	
	@Column(name="Pro_Quant")
	private int proQuant;	
	
	@Column(name="Total_Amount")
	private float totalAmount;
}
