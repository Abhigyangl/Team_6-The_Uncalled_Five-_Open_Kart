package com.openkart.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openkart.model.Cart;


public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findByBuyerId(int buyerId);
	
}
