package com.openkart.controller;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.openkart.service.SellerService;
import com.openkart.dto.ProductRequest;
import com.openkart.dto.SellerRequest;
import com.openkart.exception.AlreadyExistsException;
import com.openkart.exception.NoSuchElementException;
import com.openkart.model.*;
import com.openkart.repository.SellerRepository;

@RestController
//Seller Controller
public class SellerController {
	
	@Autowired //Autowiring Seller Service
	private SellerService sellerService;
   

	//to add Seller
	@PostMapping("/addSeller")
	public Seller addSeller(@RequestBody @Valid SellerRequest sellerRequest) 
	{
		
		//Exception :if SellerId Already Exists;
	     if(sellerService.getSellerById(sellerRequest.getSellerId()).isPresent())
			throw new AlreadyExistsException("Seller",sellerRequest.getSellerId());
		
	     //Exception :if SellerEmail Already Exists
	     if(sellerService.getSellerByemail(sellerRequest.getSellerEmail()).isPresent())
	    	 throw new AlreadyExistsException("EmailId already exist",-1);
		
	     //if seller ID or seller Email does not Exist in database
		return sellerService.addSeller(sellerRequest);
	}
	
	//to add product in database.
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody  @Valid ProductRequest productRequest) 
	{
		
		//Exception :if ProductId Already Exists;
		
	     if(sellerService.getProductById(productRequest.getProductId()).isPresent())
			throw new AlreadyExistsException("Product",productRequest.getProductId());
	     
	     //Exception : if Seller Id does not exists;
	     
	     if(!sellerService.getSellerById(productRequest.getSellerId()).isPresent())
	    	 throw new NoSuchElementException("Seller",productRequest.getSellerId());
		
		//if product id and sellerId Exist in database
		return sellerService.addProduct(productRequest);
	}
		
	//to delete product by Id
	@DeleteMapping( "/deleteProductById/{id}" )
	public Optional<Product> deleteProductById(@RequestParam ("id") int productId) 
	{
		//Exception :if ProductId does not Exists;
	     if(!sellerService.getProductById(productId).isPresent())
			throw new NoSuchElementException("Product",productId);
		
	     //if product already Exist in database
		Optional<Product> deletedProduct = null;
		Optional<Product> product = sellerService.getProductById(productId);	
		sellerService.deleteProductById(productId);
		deletedProduct = product;
		return deletedProduct;
	}
	
  //Update Product by seller using seller id and product id
	@PutMapping( "/updateProduct/{id}" )
	public Optional<Product> updateProductById( @RequestParam("productId") int productId
			,@RequestParam("sellerId") int sellerId , @RequestBody @Valid ProductRequest product)
	{
		//Exception :if ProductId Does not Exists;
	     if(!sellerService.getProductById(productId).isPresent())
			throw new NoSuchElementException("Product",productId);
	     
	    //Exception :if Seller id does not exist;
	     if(!sellerService.getSellerById(sellerId).isPresent())
			throw new NoSuchElementException("Seller",sellerId);
	     
	     
	   //if product Id and Seller ID Exist and Products belong to the seller only.
	     
	       if(sellerService.getProductById(productId).get().getSellerId()==sellerId)
	          {
		
		       Optional<Product> updatedProduct=sellerService.updateProductById(productId, product);
		       return updatedProduct;
	          }
	       
	     //Exception to check whether the product belongs to the given seller
	        else  
	          {
	    	     throw new NoSuchElementException("Product ID does not belongs to the given Seller Id",-1);
	          }
	}

    //to get product by seller Id
	@RequestMapping(value = "/getProductBySellerId", method = RequestMethod.GET, produces = "application/json")
	public java.util.List<Product> getProductBySellerId(@RequestParam("sellerId") int sellerId)
	{
		//Exception :if SellerId does not Exists;
	     if(!sellerService.getSellerById(sellerId).isPresent())
			throw new NoSuchElementException("Seller",sellerId);
	
	     // if Seller Id Exist in database.
		return sellerService.getProductBySellerId(sellerId);
	}

}
