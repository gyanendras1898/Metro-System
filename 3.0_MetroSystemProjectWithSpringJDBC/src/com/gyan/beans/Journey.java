package com.gyan.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Journey {
	
	private int cardId;
	private int boardingStationNo;
	private int destinationStationNo;
	private double fair;
	
}
