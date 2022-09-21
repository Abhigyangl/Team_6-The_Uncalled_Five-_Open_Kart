package com.openkart.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openkart.service.BuyerService;
import com.openkart.dto.BuyerRequest;
import com.openkart.dto.CartRequest;
import com.openkart.exception.AlreadyExistsException;
import com.openkart.exception.NoSuchElementException;
import com.openkart.model.*;
//Buyer Controller
@RestController
public class BuyerController {
	
	@Autowired// Autowiring Buyer Service
	private BuyerService buyerService;
	
	//To Add Buyer
	@PostMapping("/addBuyer")
	public Buyer addBuyer( @RequestBody @Valid BuyerRequest buyerRequest) 
	{
		//Exception :if BuyerId Already Exists;
	     if(buyerService.getBuyerbyId(buyerRequest.getBuyerId()).isPresent())
			throw new AlreadyExistsException("Buyer",buyerRequest.getBuyerId());
	     
	   //Exception :if BuyerEmail Already Exists;
	     if(buyerService.getBuyerByEmail(buyerRequest.getBuyerEmail()).isPresent())
			throw new AlreadyExistsException("Email already exist",-1);
	     
	
	  // if buyer Id and Email doesn't exist in Database.
		else
		     return buyerService.addBuyer(buyerRequest);
	}
	
	//To get all products
	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<Product> getAllProducts()
	{
		
		return buyerService.getAllProduct();	
	}
	
	// get product by Id 
	@RequestMapping(value = "/getProductById/{Id}", method = RequestMethod.GET, produces = "application/json")
public Optional<Product> getProductById(@PathVariable("Id") int id)
	{
		//Exception :if ProductId doesn't Exists;
	     if(!buyerService.getProductById(id).isPresent())
			throw new NoSuchElementException("Product",id);
		// if product Id exist
		return buyerService.getProductById(id);	
	}
	
	//to add product in Cart
	@PostMapping("/addProductInCart")
	public Cart addProductInCart(@RequestBody @Valid CartRequest cartRequest) 
	{
	 	//Exception :if BuyerId doesn't Exists;
	     if(!buyerService.getBuyerbyId(cartRequest.getBuyerId()).isPresent())
			throw new NoSuchElementException("Buyer",cartRequest.getBuyerId());
		
		//Exception :if ProductId doesn't Exists;
	     if(!buyerService.getProductById(cartRequest.getProId()).isPresent())
			throw new NoSuchElementException("Product",cartRequest.getProId());
	     
	     //Exception to handle quantity 
	      if(buyerService.getProductById(cartRequest.getProId()).get().getProductQuant()<cartRequest.getProQuant())
	    	  throw new NoSuchElementException("Availabe Qunatity is "+buyerService.getProductById(cartRequest.getProId()).get().getProductQuant(),-1);
		
	      // adding product in cart
		Cart cart=new Cart();
		 cart=buyerService.addProductInCartById(cartRequest);
		return cart;
	}
	
  //Functionality to buy product by cart id.
	@PostMapping("/BuyProductByCartId/{Id}")
	public BuyProduct BuyProductByCartId(@RequestParam("cartId") int cartId,@RequestParam("buyerId") int buyerId) 
	{
		//Exception :if CartId doesn't Exists;
	     if(!buyerService.getCartById(cartId).isPresent())
			throw new NoSuchElementException("Cart",cartId);
	     
	     //Exception if CartId doesn't belongs to buyer id.
	     
	     if(buyerService.getCartById(cartId).get().getBuyerId()==buyerId)
         {
	
	 		BuyProduct BuyedProduct=new BuyProduct();
			BuyedProduct=buyerService.addProductInBuyProduct(cartId);
			
			
			return BuyedProduct;
         }
      
	     //Buying Product
       else  
         {
   	     throw new NoSuchElementException("Cart ID does not belongs to the given Buyer Id",-1);
         }
		

	}
	//to get cart by Id
	@RequestMapping(value = "/getCartByBuyerId", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<Cart> getCartByBuyerId(@RequestParam("buyerId") int buyerId)
	{
		//Exception :if BuyerId doesn't Exists;
	     if(!buyerService.getBuyerbyId(buyerId).isPresent())
			throw new NoSuchElementException("Buyer",buyerId);
		
	     //if buyer Id Exist.
		return buyerService.getCartByBuyerId(buyerId);
	}
}
