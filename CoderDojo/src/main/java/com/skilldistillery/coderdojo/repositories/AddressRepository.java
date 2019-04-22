package com.skilldistillery.coderdojo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.coderdojo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {


}
