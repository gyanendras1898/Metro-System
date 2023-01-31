package com.gyan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gyan.beans.Card;

public class CardRowMapper implements RowMapper<Card> {

	@Override
	public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("id");
		double balance = rs.getDouble("balance");
		Card card = new Card(id,balance);
		return card;
	}

}
