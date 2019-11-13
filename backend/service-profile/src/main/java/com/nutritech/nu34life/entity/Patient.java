package com.nutritech.nu34life.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nutritech.nu34life.util.Account;

@Data
@Entity
@Table(name = "patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(targetEntity = Account.class)
	@JoinColumn(name = "user_id")
	private Long idUser;
	
    @ManyToOne
    @JoinColumn(name = "current_nutritionist")
    private Nutritionist nutritionist;
}