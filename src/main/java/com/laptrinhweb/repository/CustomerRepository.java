package com.laptrinhweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.Entity.CustomerEntity;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
