package com.openkart.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;


import com.openkart.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>
{
	Optional<Seller> findBySellerEmail(String email);

}
