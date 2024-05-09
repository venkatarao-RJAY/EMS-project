package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.DeliveryPerson;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Integer>{

}
