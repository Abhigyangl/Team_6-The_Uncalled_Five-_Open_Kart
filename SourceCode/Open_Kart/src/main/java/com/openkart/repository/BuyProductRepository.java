package com.openkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openkart.model.BuyProduct;
@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, Integer> {

}
