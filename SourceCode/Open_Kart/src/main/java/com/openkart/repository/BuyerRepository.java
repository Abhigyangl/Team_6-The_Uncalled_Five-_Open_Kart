package com.openkart.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openkart.model.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>
{
	Optional<Buyer> findByBuyerEmail(String email);
}
