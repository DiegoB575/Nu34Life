package com.nutritech.nu34life.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutritech.nu34life.model.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{
	
}