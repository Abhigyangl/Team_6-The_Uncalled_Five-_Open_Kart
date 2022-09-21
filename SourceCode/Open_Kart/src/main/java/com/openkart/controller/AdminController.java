package com.openkart.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openkart.exception.NoSuchElementException;
import com.openkart.exception.ResourceNotFoundException;
import com.openkart.model.BuyProduct;
import com.openkart.model.Buyer;
import com.openkart.model.Product;
import com.openkart.model.Seller;
import com.openkart.service.BuyerService;
import com.openkart.service.SellerService;

@RestController
// Admin Controller
public class AdminController {
	
	@Autowired  // Buyer Service
	private BuyerService buyerService;
	@Autowired // Seller Service
	private SellerService sellerService;
	
	
	//buyer controller // To get All Buyers
	@RequestMapping(value = "/getAllBuyer", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<Buyer> getAllBuyers(){
		
		return buyerService.getAllBuyer();	
	}
	
	//Buyer Controller //To get Buyer by Id
	@RequestMapping(value = "/getBuyerById/{Id}", method = RequestMethod.GET, produces = "application/json")
public Optional<Buyer> getBuyerById(@PathVariable("Id") int id)
	{
	  //Exception : if Buyerid is not present in DB.
		
		if(!buyerService.getBuyerbyId(id).isPresent())
		    throw new NoSuchElementException("Buyer",id);
		
		else
		  return buyerService.getBuyerbyId(id);	
	}
	
	//Buyer Controller //To delete Buyer by Id
	@DeleteMapping( "/deleteBuyerById/{id}" )
	public Optional<Buyer> deleteBuyerById(@PathVariable("id") int id) 
	{
        
		//Exception : if Buyerid is not present in DB.
		
		if(!buyerService.getBuyerbyId(id).isPresent())
		    throw new NoSuchElementException("Buyer",id);
		
		
		// if buyer Id is present
		Optional<Buyer> deletedBuyer = null;
		Optional<Buyer> Buyer = buyerService.getBuyerbyId(id);
		buyerService.deleteBuyerbyId(id);
		deletedBuyer = Buyer;
		return deletedBuyer;
	}
	
	//Seller controller //to get all sellers
	@RequestMapping(value = "/getAllSeller", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<Seller> getAllSeller(){
		
		return sellerService.getAllSeller()	;
	}
	
	//Seller controller //to get seller by ID
	@RequestMapping(value = "/getSellerById/{Id}", method = RequestMethod.GET, produces = "application/json")
public Optional<Seller> getSellerById(@PathVariable("Id") int id)
	{
	    //Exception : if SellerId is not present in DB.
		
		if(!sellerService.getSellerById(id).isPresent())
		    throw new NoSuchElementException("Seller",id);
		//if seller Id is present
		return sellerService.getSellerById(id);
	}
	//Seller controller //to delete seller by ID
	@DeleteMapping( "/deleteSellerrById/{id}" )
	public Optional<Seller> deleteSellerById(@RequestParam("id") int id) 
	{
		    //Exception : if Sellerid is not present in DB.
				if(!sellerService.getSellerById(id).isPresent())
				    throw new NoSuchElementException("Seller",id);
				
		//if seller id is present in DB.
		Optional<Seller> deletedSeller = null;
		Optional<Seller> Seller = sellerService.getSellerById(id);	
		sellerService.deleteSellerBYId(id);
		deletedSeller = Seller;
		return deletedSeller;
	}
	
	
	//Product controller //to get all products.
	@RequestMapping(value = "/getAllProductsAllSellers", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<Product> getAllProducts(){
		
		return buyerService.getAllProduct();	
	}
	
	//Product controller //to get all bought products.
	@RequestMapping(value = "/getAllBuyedProduct", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<BuyProduct> getAllBuyedProduct(){
		
		return buyerService.getAllBuyedProduct();
	}
	
	

	
}
