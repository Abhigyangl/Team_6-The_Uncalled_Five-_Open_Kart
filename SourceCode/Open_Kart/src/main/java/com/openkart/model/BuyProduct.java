package com.openkart.model;

import java.util.UUID;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Buyhistory")
public class BuyProduct {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="Payment_Id")	
	private int paymentId;
	
	@Column(name="Cart_Id")
	private int cartId;
	
	@Column(name="Buyer_Id")
	private int buyerId;
	
	@Column(name="Pro_Id")
	private int proId;
	
	@Column(name="Total_Amount")
	private float totalAmount;
	
	@Column(name="Transaction_Id")
	private UUID transactionId;
	

}
