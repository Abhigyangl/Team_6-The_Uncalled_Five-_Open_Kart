package com.openkart.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.openkart.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
