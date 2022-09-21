package com.openkart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="buildSeller")
@NoArgsConstructor
@Entity
@Table(name="Seller")
public class Seller 
{
	@Id
	@Column(name="Seller_Id")
	private int sellerId;
	
	@Column(name="Seller_Name")
	private String sellerName;
	
	@Column(name="Seller_Phone")
	private String sellerPhone;
	
	@Column(name="Seller_Email")
	private String sellerEmail;
	
	@Column(name="Seller_Password")
	private String sellerPassword;
	
	@Column(name="Seller_Address")
	private String sellerAddress;
}