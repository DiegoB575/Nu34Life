package com.nutritech.entity;

import java.util.List;

import javax.persistence.*;



import lombok.Data;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "servings")
	private Integer servings;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "recipe_id")
	private List<Step> steps;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "recipe_id", insertable = false, updatable = false)
	private List<Ingredient> ingredients;
	
	@OneToOne
	@JoinColumn(name = "nutr_fact_id")
	private NutritionFact nutrFact;
}