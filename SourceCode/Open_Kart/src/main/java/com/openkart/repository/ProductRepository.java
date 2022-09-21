package com.openkart.repository;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.openkart.model.Cart;
import com.openkart.model.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findBySellerId(int sellerId);

	void deleteBySellerId(int sellerId);

}
