package com.openkart.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openkart.dto.BuyerRequest;
import com.openkart.dto.CartRequest;
import com.openkart.model.BuyProduct;
import com.openkart.model.Buyer;
import com.openkart.model.Cart;
import com.openkart.model.Product;
import com.openkart.repository.BuyProductRepository;
import com.openkart.repository.BuyerRepository;
import com.openkart.repository.CartRepository;
import com.openkart.repository.ProductRepository;
@Service
// Buyer Service 
public class BuyerService {
	
	@Autowired
	private BuyerRepository buyerRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private BuyProductRepository buyProductRepo;

	//Method To add Buyer
	public Buyer addBuyer(BuyerRequest buyerRequest)
	{
		//Creating object of Class Buyer to save the data.
		Buyer newBuyer=Buyer.build(buyerRequest.getBuyerId(), buyerRequest.getBuyerName(), buyerRequest.getBuyerPhone()
				, buyerRequest.getBuyerEmail(), buyerRequest.getBuyerPassword(), buyerRequest.getBuyerAddress());
		return buyerRepo.save(newBuyer);
	}

	// To get List of all Buyers.
	public List<Buyer>getAllBuyer()
	{
		return buyerRepo.findAll();
	}
	
	//to get buyer by buyerId
	public Optional<Buyer> getBuyerbyId(int id)
	{
		return buyerRepo.findById(id);
		
	}
	
	//to delete Buyer by Id
	public void deleteBuyerbyId(int id)
	{
		List<Cart> cart1 = cartRepo.findByBuyerId(id); 
		
		//for deleting all cart which is added by BuyeriD.
		for(int i=0;i<cart1.size();i++)
		{
			if(cart1.get(i).getBuyerId()==id)
			{
				cartRepo.deleteById(cart1.get(i).getCartId());
			}
		}
		buyerRepo.deleteById(id);
	} 
	
	// To Get List of all products
	public List<Product> getAllProduct()
	{
		return productRepo.findAll();
	}
	
	//to get product by ProductID
	public Optional<Product> getProductById(int id)
	{
		Optional<Product> product=productRepo.findById(id);
		return product;
	}
	
	//to add product in cart
	public Cart addProductInCartById(CartRequest cartRequest)
	{
		Optional<Product> product=getProductById(cartRequest.getProId());
		float totalAmount=((product.get().getProductPrice())*(cartRequest.getProQuant()));
		Cart newCart=Cart.buildCart(0,cartRequest.getBuyerId(), cartRequest.getProId(), product.get().getProductName()
				,product.get().getProductPrice(),cartRequest.getProQuant(),totalAmount);
		cartRepo.save(newCart);
		return newCart;
	}
	//to get list of Cart by BuyerID
	public List<Cart> getCartByBuyerId(int buyerId)
	{
	return cartRepo.findByBuyerId(buyerId);
	
	}
	
	//to buy the product by cart id.
	public BuyProduct addProductInBuyProduct(int cartId) 
	{
		
		UUID uuid=UUID.randomUUID(); 
		Optional<Cart> cart=cartRepo.findById(cartId);
		BuyProduct buyProduct=new BuyProduct();
		buyProduct.setPaymentId(0);
		buyProduct.setBuyerId(cart.get().getBuyerId());
		buyProduct.setCartId(cart.get().getCartId());
		buyProduct.setProId(cart.get().getProId());
		buyProduct.setTotalAmount(cart.get().getTotalAmount());
		buyProduct.setTransactionId(uuid);
		buyProductRepo.save(buyProduct);
		cartRepo.deleteById(cart.get().getCartId());
	
		
		//updating product quantity after buying.
		Optional<Product> p2=productRepo.findById(cart.get().getProId());
		p2.get().setProductQuant(p2.get().getProductQuant()-cart.get().getProQuant());
		productRepo.save(p2.get());
		return buyProduct;
	}
 
	//Function to return cart by using cartId
	 public Optional<Cart> getCartById(int id)
	 {
		 
		 Optional<Cart> cart = cartRepo.findById(id);
		 return cart;
		 
	 }
	
	//to get buyer by emailID
	 public Optional<Buyer> getBuyerByEmail(String email)
	 {
		 Optional<Buyer> buyer = buyerRepo.findByBuyerEmail(email);
		 return buyer;
	 }
	 
	
	//to get all bought product 
	public List<BuyProduct> getAllBuyedProduct() 
	{
		return 	buyProductRepo.findAll();
	}

	
}

