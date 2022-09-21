package com.openkart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="build")
@Entity
@Table(name="buyer")
public class Buyer 
{
	@Id
	@Column(name="Buyer_Id")
	private int buyerId;
	
	@Column(name="Buyer_Name")
	private String buyerName;
	
	@Column(name="Buyer_Phone")
	private String buyerPhone;
	
	@Column(name="Buyer_Email")
	private String buyerEmail;
	
	@Column(name="Buyer_Password")
	private String buyerPassword;
	
	@Column(name="Buyer_Address")
	private String buyerAddress;
}
