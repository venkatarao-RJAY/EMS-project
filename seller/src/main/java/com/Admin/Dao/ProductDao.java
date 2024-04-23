package com.Admin.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Admin.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

}
