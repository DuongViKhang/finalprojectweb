package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.Entity.LoginEntity;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
	List<LoginEntity> findByUsername(String username);
}