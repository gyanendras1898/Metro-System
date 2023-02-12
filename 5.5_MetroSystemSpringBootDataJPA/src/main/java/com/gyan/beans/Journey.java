package com.gyan.beans;

import java.sql.Date;

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
public class Journey {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int number;
	private int cardId;
	private int boardingStationNo;
	private Date swipeIn;
	private int destinationStationNo;
	private Date swipeOut;
	private double fair;
	
	public Journey(int cardId, int boardingStationNo, Date swipeIn, int destinationStationNo, Date swipeOut,
			double fair) {
		super();
		this.cardId = cardId;
		this.boardingStationNo = boardingStationNo;
		this.swipeIn = swipeIn;
		this.destinationStationNo = destinationStationNo;
		this.swipeOut = swipeOut;
		this.fair = fair;
	}	
}
