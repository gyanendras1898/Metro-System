package com.gyan.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int number;
	private String name;
	
	public Station(String name) {
		super();
		this.name = name;
	}
	
	
}
