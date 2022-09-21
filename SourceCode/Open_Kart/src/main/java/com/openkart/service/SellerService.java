package com.openkart.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openkart.dto.ProductRequest;
import com.openkart.dto.SellerRequest;
import com.openkart.model.Buyer;
import com.openkart.model.Cart;
import com.openkart.model.Product;
import com.openkart.model.Seller;
import com.openkart.repository.ProductRepository;
import com.openkart.repository.SellerRepository;

@Service
//Seller Service
public class SellerService 
{
	@Autowired
    SellerRepository sellerRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	
	//To add Seller
	   public Seller addSeller(SellerRequest sellerRequest)
	   {
		   Seller newSeller=Seller.buildSeller(sellerRequest.getSellerId(), sellerRequest.getSellerName(), sellerRequest.getSellerPhone()
					, sellerRequest.getSellerEmail(), sellerRequest.getSellerPassword(), sellerRequest.getSellerAddress());
		   return sellerRepo.save(newSeller);
	   }
	   
	
	//To find all seller
		public List<Seller >getAllSeller()
		{
			return sellerRepo.findAll();
		}
		
		
	//To find Seller By Id
		public Optional<Seller> getSellerById(int id)
		{
			return sellerRepo.findById(id);	
		}
		
		
	//To Find Seller By sellerEmail	
		public Optional<Seller> getSellerByemail(String email)
		{
			return sellerRepo.findBySellerEmail(email);
		}
		
		
	//Delete Seller By Seller ID
		public void deleteSellerBYId(int sellerId)
		{
			sellerRepo.deleteById(sellerId);
			List<Product> l1=  getProductBySellerId( sellerId);
			//deleting all products which is added by seller only.
			for (int i=0;i<l1.size();i++)
			{
				if(l1.get(i).getSellerId()==sellerId)
				{
					productRepo.deleteById(l1.get(i).getProductId());
				}
			}
		}
  
					
			
			//product Related Function
			//Add Product
			public Product addProduct(ProductRequest productRequest)
			{
				Product newProduct=Product.buildProduct(productRequest.getProductId(),productRequest.getSellerId(), productRequest.getProductName()
						, productRequest.getProductType(), productRequest.getProductPrice()
						, productRequest.getProductDesc(), productRequest.getProductQuant());
				return productRepo.save(newProduct);	
			}
			
			
			//Get Product By Id
			public Optional<Product> getProductById(int productId)
			{
				return productRepo.findById(productId);	
			}
			
			
			//Delete Product By  Product id
			public void deleteProductById(int productId)
			{
				productRepo.deleteById(productId);	
			}
			
			
			//Update Product By ProductId
			public Optional<Product> updateProductById(int productId, ProductRequest product)
			{
				Optional<Product> updatedProduct=productRepo.findById(productId);
				updatedProduct.get().setProductName(product.getProductName());
				updatedProduct.get().setProductType(product.getProductType());
				updatedProduct.get().setProductPrice(product.getProductPrice());
				updatedProduct.get().setProductDesc(product.getProductDesc());
		        updatedProduct.get().setProductQuant(product.getProductQuant());
		        
		        ProductRequest proReq=new ProductRequest();
		        
		        proReq.setProductId(updatedProduct.get().getProductId());
		        proReq.setSellerId(updatedProduct.get().getSellerId());
		        proReq.setProductName(updatedProduct.get().getProductName());
		        proReq.setProductType(updatedProduct.get().getProductType());
		        proReq.setProductPrice(updatedProduct.get().getProductPrice());
		        proReq.setProductDesc(updatedProduct.get().getProductDesc());
		        proReq.setProductQuant(updatedProduct.get().getProductQuant());
		        addProduct(proReq);
		        return updatedProduct;
			}

			
			//to get all products by seller ID
			public List<Product> getProductBySellerId(int sellerId) {
				return productRepo.findBySellerId(sellerId);				
			}
		

}
