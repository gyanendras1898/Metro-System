package com.gyan.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double balance;
	
	public Card(double balance) {
		super();
		this.balance = balance;
	}
	
}
